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

const Boards=()=>{

    //등록/수정/삭제처리후 이동 처리를 위한 useNavigate()훅 함수객체 생성
    const navigate=useNavigate();
    //1.모든 글 가져오기:반환값은 [상태값,세터]형태이다(커스텀 훅 호출)
    const [boards,setBoards] = useFetch('http://localhost:3002/bbs?_sort=id&_order=desc');
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
                 //State(boards)변경:스프레드 연산자.map()함수도 가능
                setBoards(prevState=>[res.data,...prevState]);
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
                //State(boards)변경: filter()함수 적용
                setBoards(boards.filter(board=>board.id != id));
                //삭제후 게시판 루트(목록)로 이동
                navigate('/boards');
             })
             .catch(e=>console.log('삭제시 오류:',e));
         
    };

    return <>
        {/*현재 컴포넌트(Boards)를 최상위 컴포넌트로 하여 라우팅 설정
            (목록 혹은 입력/수정폼 혹은 상세보기로 갈수 있게)
            또한 최상위 컴포넌트인 App에 Boards로 올 수 있는 라우팅을 
            추가해야 한다
        */}
        <Routes>
            <Route path="" element={<List boards={boards}/>}/>    
            <Route path="view/:id" element={<View handleBoardDelete={handleBoardDelete}/>}/>           
            <Route path="form" element={<Form handleBoardCreate={handleBoardCreate}/>} />   
            <Route path="form/:id" element={<EditForm handleBoardUpdate={handleBoardUpdate}/>}/>
        </Routes> 
    </>

};

export default  Boards;