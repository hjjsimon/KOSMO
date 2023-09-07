//페이징 구현하기
//https://www.npmjs.com/package/rc-pagination
//index.js에 import 'rc-pagination/assets/index.css';추가

import { Route, Routes, useNavigate } from "react-router-dom";
import List from "./bbs/list";
import Form from "./bbs/form";
import View from "./bbs/view";
import { fetchReducer } from "../hooks/useDataFetch";
import axios from "axios";
import EditForm from "./bbs/form_edit";
import { useEffect, useReducer, useState } from "react";
import { fetches } from "../utils/utility";
import bbsReducer from "../reducers/bbs_reducer";
import { BoardsContext } from "../context/bbs_context";
const initialState={
    isLoading:false,
    boards:[],
    pageSize:5,
    totalSize:0,
    nowPage:1
}
const BoardsPaging=()=>{

    //State를 useState()대신 useReducer()로 관리
    const [boardsInfo,dispatch] = useReducer(bbsReducer,initialState);
    const {nowPage,pageSize,boards}=boardsInfo;

    //등록/수정/삭제처리후 이동 처리를 위한 useNavigate()훅 함수객체 생성
    const navigate=useNavigate();


    const fetchBoards=()=>{
        //패치전:로딩 시작
        dispatch({type:'loading',isLoading:true});
        try{
            fetchReducer(`http://localhost:3002/bbs?_sort=id&_order=desc&_page=${boardsInfo.nowPage}&_limit=${boardsInfo.pageSize}`)
                .then(res=>{
                    //데이타는 네트웍을 통해 페칭 완료
                    //화면에 보여줄 State를 변경하자                    
                    //페피한 데이타로 글 목록을 변경해달라고 요청
                    dispatch({type:'all',boards:res.data});
                    
                })
                .catch(e=>console.log(e));
             //패치전:로딩 끝
             dispatch({type:'loading',isLoading:false});     
        }
        catch(e){console.log(e);}
    };

    //총 글 가져오기
    useEffect(()=>fetchBoards(),[boardsInfo.nowPage]);//의존성배열에 boardsInfo.boards추가시 무한루프
    
    
    
    //총 글수 설정하기
    useEffect(()=>{
        axios.get('http://localhost:3002/bbs')
             .then(res=>{
                console.log('총 글 수:',res.data.length)
                dispatch({type:'total',total:res.data.length});
                })
             .catch(e=>console.log(e))      
      //위의 총글수 State를 변경하지만 무한반복은 안 일어난다
      //왜냐하면 콜백함수는 총글수 State가 아니라 boards가 의존하니까          
    },[boardsInfo.boards]);//글 등록하거나 삭제시 총 글수를 다시 업데이트 하기 위함
    
    
    
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
                fetchReducer(`http://localhost:3002/bbs?_sort=id&_order=desc&_page=${nowPage}&_limit=${pageSize}`).then(res=>dispatch({type:'write',boards:res.data}));
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
                    //setBoards(boards.map(item=> item.id===board.id ?{...board,title:board.title,content:board.content}:item)) ;
                    dispatch({type:'update',board:{id:board.id,title:board.title,content:board.content}})
                    //수정후 상세보기로 이동
                    navigate(`/boards/view/${board.id}`,{state:board});
             })
             .catch(e=>console.log('수정시 오류:',e))

    };

    //4.게시글 삭제 함수:View컴포넌트로 내린다
    const handleBoardDelete=id=>{
        //데이타베이스에서 삭제
        axios.delete(`http://localhost:3002/bbs/${id}`)
             .then(res=>{
                
                fetchReducer(`http://localhost:3002/bbs?_sort=id&_order=desc&_page=${nowPage}&_limit=${pageSize}`).then(res=>dispatch({type:'delete',boards:res.data}));
                
                //삭제후 게시판 루트(목록)로 이동
                navigate('/boards');
             })
             .catch(e=>console.log('삭제시 오류:',e));
         
    };

    //List컴포넌트에 페이징과 관련된 값들
    //setNowPage:List컴포넌트에서 페이지 번호 누를때 nowPage스테이트를 변경하기 위해서
    

    return <>
        {/*현재 컴포넌트(Boards)를 최상위 컴포넌트로 하여 라우팅 설정
            (목록 혹은 입력/수정폼 혹은 상세보기로 갈수 있게)
            또한 최상위 컴포넌트인 App에 Boards로 올 수 있는 라우팅을 
            추가해야 한다
        */}
        <BoardsContext.Provider value={{dispatch,boardsInfo,handleBoardCreate,handleBoardDelete,handleBoardUpdate}}>
            <Routes>
                <Route path="" element={<List/>}/>    
                <Route path="view/:id" element={<View />}/>           
                <Route path="form" element={<Form />} />   
                <Route path="form/:id" element={<EditForm/>}/>
            </Routes> 
        </BoardsContext.Provider>
    </>

};

export default  BoardsPaging;