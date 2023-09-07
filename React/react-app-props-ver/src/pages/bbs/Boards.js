//페이징 구현하기
//https://www.npmjs.com/package/rc-pagination
//index.js에 import 'rc-pagination/assets/index.css';추가

import axios from "axios";
import { useEffect, useState } from "react";
import { Route, Routes, useNavigate } from "react-router-dom";
import useFetch from "../../hooks/useFetch";
import Form from "./Form";
import List from "./List";
import View from "./View";

import {fetches} from "../../utils/utilities"

const Boards=()=>{

  //등록/수정/삭제처리후 이동 처리를 위한 훅 함수객체 생성
  const navigate=useNavigate();
  //1.모든 글 가져오기:반환값은 [상태값,세터]형태이다(커스텀 훅 호출)
  //const [boards,setBoards]=useFetch(`http://localhost:3002/bbs`);
  //[페이징 시작 코드]
  //_page=페이지번호&_limit=한 페이지에 보여줄 글수  
  const [pageSize,setPageSize]= useState(5);//페이지당 글 갯수
  const [totalSize,setTotalSize]=useState(0);//총 글수
  const [nowPage,setNowPage]= useState(1);//현재 페이지
  //페이징 시에는 마운트 될때 그리고 페이지 번호(nowPage)가 변할때마다 글 가져오기(의존상 배열:[nowPage])
  //페이징 안할때는 의존성 배열:[] 즉 마운트 될때만 전체 목록 가져오기
  const [boards,setBoards]=useFetch(`http://localhost:3002/bbs?_page=${nowPage}&_limit=${pageSize}&_sort=id&_order=desc`,[nowPage]);
  
  
  //총 글수 설정하기
  //[] -> [boards]로 변경:글 등록하거나 삭제시 총 글수를 다시 업데이트 하기 위함
  useEffect(()=>{
    axios.get(`http://localhost:3002/bbs`)
    .then(res=>setTotalSize(res.data.length));
  },[boards]);
  //[페이징 끝 코드]
  

  //2.게시글 등록 함수-Form 컴포넌트로 내린다
  const handleBoardCreate = (title,content)=>{
    
    const date = new Date();
    const postDate = `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()}`;
    //※JSON-SERVER는 id를 자동으로 생성해 준다
    const board = {
      username:sessionStorage.getItem('username'),
      title,
      content,
      postDate,
      visitCount:0
    };
    axios.post('http://localhost:3002/bbs',board)
      .then(res=>{  
        
        //글 추가:페이징 미 적용시
        //setBoards(prevState=>[res.data,...prevState]);
        //글 추가:페이징시 1페이지에 계속 추가된다 이 문제를 해결하기 위해 Array.slice (i.e. _start is inclusive and _end exclusive)
        setBoards(prevState=>[res.data,...prevState].slice(0,pageSize));
        //console.log('등록 후 총 글수:',totalSize);
        //글 등록시(혹은 삭제시) 페이징을 위한 총 글수가 업데이트가 안된다. 그래서 페이지 번호가 추가되거나 줄어들지 안는다
        //등록후 게시판 루트(목록)로 이동
        navigate('/boards');
      }).catch(e=>console.log('입력시 오류:',e))  
  };//등록 끝
  //3.게시글 수정 함수:Form컴포넌트로 내린다
  const handleBoardUpdate=(board)=>{//board는 수정용 객체를 인자로 받는다
    axios.put(`http://localhost:3002/bbs/${board.id}`,board)
    .then(res=>{
      //글 수정-map()함수
      setBoards(boards.map(element=>
        element.id===board.id ?
        {...element,title:board.title,content:board.content} :
        element
        ));
      //수정후 게시판 상세보기로 이동
      navigate(`/boards/view/${board.id}`);
    })
    .catch(e=>console.log('수정시 오류:',e))

  };//수정 끝
  //4.게시글 삭제 함수:View컴포넌트로 내린다
  
  

  const hanldeBoardDelete=id=>{
    axios.delete(`http://localhost:3002/bbs/${id}`)
    .then(res=>{
      //글 삭제 : 페이징 미 적용시(filter()함수)      
      //setBoards((prevState)=>boards.filter(board=>board.id !==id));      
      //글 삭제 : 페이징 적용시 삭제시 마다 글이 하나씩 줄어든다
      //해결책 : 다시 페치해 온다(useFetch() 커스텀 훅 호출불가-콜백힘수안에서 호출불가)
      //훅  규칙:최상위(at the Top Level)에서만 Hook을 호출해야 한다
      fetches(`http://localhost:3002/bbs?_page=${nowPage}&_limit=${pageSize}&_sort=id&_order=desc`).then(resp=>setBoards([...resp]));
      
      //삭제후 게시판 루트(목록)로 이동
      navigate('/boards');
    })
    .catch(e=>console.log(e));

  };//삭제 끝

  return <>
  {/*현재 컴포넌트(Boards)를 최상위 컴포넌트로 하여 라우팅 설정
     (목록 혹은 입력/수정폼 혹은 상세보기로 갈수 있게)
     또한 최상위 컴포넌트인 App에 Boards로 올 수 있는 라우팅을 
     추가해야 한다
  */}  
    <Routes>
      {/* <Route path="" element={<List boards={boards}/>}/>    */}
      {/* 페이징 적용시
        setNowPage:페이지 번호 누를때 nowPage스테이트를 변경하기 위해서
      */}
      <Route path="" element={<List boards={boards} pageSize={pageSize} totalSize={totalSize} nowPage={nowPage} setNowPage={setNowPage}/>}/>
      <Route path="view/:id" element={<View hanldeBoardDelete={hanldeBoardDelete}/>}/>
      {/*Form컴포넌트 하나로 수정/등록 사용*/}
      <Route path="form" element={<Form handleBoardCreate={handleBoardCreate} handleBoardUpdate={handleBoardUpdate}/>}/>
    </Routes>
  </>
};


export default Boards;