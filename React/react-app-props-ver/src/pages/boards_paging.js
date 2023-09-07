//페이징 구현하기
//https://www.npmjs.com/package/rc-pagination
//index.js에 import 'rc-pagination/assets/index.css';추가

import { Route, Routes, useNavigate } from "react-router-dom";
import List from "./bbs/list";
import Form from "./bbs/form";
import View from "./bbs/view";
import useFetch from "../hooks/useDataFetch";
import axios from "axios";
import EditForm from "./bbs/form_edit";
import { useEffect, useState } from "react";
import { fetches } from "../utils/utility";

const BoardsPaging=()=>{

    //등록/수정/삭제처리후 이동 처리를 위한 useNavigate()훅 함수객체 생성
    const navigate=useNavigate();
    //1.페이징시 보여줄 글수만큼 가져오기
    //_page=페이지번호&_limit=한 페이지에 보여줄 글수  
    const [pageSize,setPageSize] = useState(5);//페이지당 글 갯수
    const [totalSize,setTotalSize] = useState(0);//총 글수
    const [nowPage,setNowPage]= useState(1)//현재 페이지
    //페이징 시에는 마운트 될때 그리고 페이지 번호(nowPage)가 변할때마다 글 가져오기(의존상 배열:[nowPage])
    //페이징 안할때는 의존성 배열:[] 즉 마운트 될때만 전체 목록 가져오기
    console.log('nowPage:%s,pageSize:%s',nowPage,pageSize);
    const [boards,setBoards] = useFetch(`http://localhost:3002/bbs?_sort=id&_order=desc&_page=${nowPage}&_limit=${pageSize}`,[nowPage]);
    console.log('글 목록:',boards);
    //총 글수 설정하기
    useEffect(()=>{
        axios.get('http://localhost:3002/bbs')
             .then(res=>{
                console.log('총 글 수:',res.data.length)
                setTotalSize(res.data.length);
                })
             .catch(e=>console.log(e))

    },[boards]);//글 등록하거나 삭제시 총 글수를 다시 업데이트 하기 위함
    
    
    
    //2.게시글 등록 함수-Form 컴포넌트로 내린다
    const handleBoardCreate=(title,content)=>{
        console.log('handleBoardCreate()함수 호출');
        const date = new Date();
        const postDate=`${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()}`;
        //※JSON-SERVER는 id를 자동으로 생성해 준다
        const board={
            title,
            content,
            username:sessionStorage.getItem('username'),
            postDate,
            visitCount:0

        };
         //데이타베이스에서 등록
        axios.post('http://localhost:3002/bbs',board)
             .then(res=>{
                console.log('글 등록후 응답:',res);
                 //State(boards)변경:스프레드 연산자.map()함수도 가능(res.data는 등록된 데이타)
                 //페이징시 1페이지에 계속 추가된다 이 문제를 해결하기 위해 Array.slice (i.e. _start is inclusive and _end exclusive)
                 setBoards(prevState=>[res.data,...prevState].slice(0,pageSize));
                //등록후 목록으로 이동
                navigate('/boards');
             })
             .catch(e=>console.log(e))
    };
    
    //3.게시글 수정 함수:Form컴포넌트로 내린다
    const handleBoardUpdate=(board)=>{
        console.log('(수정함수에서 받은 글:',board);
         //데이타베이스에서 수정
        axios.put(`http://localhost:3002/bbs/${board.id}`,board)
             .then(res=>{
                    //State(boards)변경: map()함수 적용
                   
                    setBoards(boards.map(item=> item.id===board.id ?{...board,title:board.title,content:board.content}:item)) ;
                    //수정후 상세보기로 이동
                    navigate(`/boards/view/${board.id}`);
             })
             .catch(e=>console.log('수정시 오류:',e))

    };

    //4.게시글 삭제 함수:View컴포넌트로 내린다
    const handleBoardDelete=id=>{
        //데이타베이스에서 삭제
        axios.delete(`http://localhost:3002/bbs/${id}`)
             .then(res=>{
                //State(boards)변경: filter()함수 적용-페이징 미 적용시
                //setBoards(boards.filter(board=>board.id != id));
                 //글 삭제 : 페이징 적용시 삭제시 마다 글이 하나씩 줄어든다
                //해결책 : 다시 페치해 온다(useFetch() 커스텀 훅 호출불가-콜백힘수안에서 호출불가)
                //훅  규칙:최상위(at the Top Level)에서만 Hook을 호출해야 한다
                fetches(`http://localhost:3002/bbs?_sort=id&_order=desc&_page=${nowPage}&_limit=${pageSize}`).then(res=>setBoards([...res]));
                
                //삭제후 게시판 루트(목록)로 이동
                navigate('/boards');
             })
             .catch(e=>console.log('삭제시 오류:',e));
         
    };

    //List컴포넌트에 페이징과 관련된 값들
    //setNowPage:List컴포넌트에서 페이지 번호 누를때 nowPage스테이트를 변경하기 위해서
    const paging = {pageSize,totalSize,nowPage,setNowPage}

    return <>
        {/*현재 컴포넌트(Boards)를 최상위 컴포넌트로 하여 라우팅 설정
            (목록 혹은 입력/수정폼 혹은 상세보기로 갈수 있게)
            또한 최상위 컴포넌트인 App에 Boards로 올 수 있는 라우팅을 
            추가해야 한다
        */}
        <Routes>
            <Route path="" element={<List boards={boards}  paging={paging}/>}/>    
            <Route path="view/:id" element={<View handleBoardDelete={handleBoardDelete}/>}/>           
            <Route path="form" element={<Form handleBoardCreate={handleBoardCreate}/>} />   
            <Route path="form/:id" element={<EditForm handleBoardUpdate={handleBoardUpdate}/>}/>
        </Routes> 
    </>

};

export default  BoardsPaging;