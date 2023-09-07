import { Link, useLocation, useNavigate, useParams } from "react-router-dom";

import React, { useContext, useEffect, useState } from "react";
import { BoardsContext } from "../../context/bbs_context";
import { fetchReducer } from "../../hooks/useDataFetch";
import axios from "axios";

const View = () => {
    const {handleBoardDelete} = useContext(BoardsContext);
    const navigate = useNavigate();

    //1.URL파라미터로 넘어오는 게시글의 키(ID)값 받기
    const params = useParams();
    console.log("글 번호:", params.id);
    //2.id에 해당하는 글 읽어 오기
    const {state}=useLocation();
  
    console.log("상세보기의 state:", state);
    
    return (
        <>
        
        <div className="jumbotron">
            <h1>
            게시판 <small>상세보기 페이지</small>
            </h1>
        </div>
        <table className="table table-bordered">
            <tbody className="table-sm">
            <tr>
                <th className="w-25 bg-dark text-white text-center">번호</th>
                <td>{state.id}</td>
            </tr>
            <tr>
                <th className="w-25 bg-dark text-white text-center">글쓴이</th>
                <td>{state.username}</td>
            </tr>
            <tr>
                <th className="w-25 bg-dark text-white text-center">작성일</th>
                <td>{state.postDate}</td>
            </tr>
            <tr>
                <th className="w-25 bg-dark text-white text-center">조회수</th>
                <td>{state.visitCount}</td>
            </tr>
            <tr>
                <th className="w-25 bg-dark text-white text-center">제목</th>
                <td>{state.title}</td>
            </tr>
            <tr>
                <th className="bg-dark text-white text-center" colSpan="2">
                내 용
                </th>
            </tr>
            <tr>
                
                <td colSpan="2">{state.content && state.content.split('\n').map((text,index)=><React.Fragment key={index}>{text}<br/></React.Fragment>)}</td>
            </tr>
            </tbody>
        </table>
        <div className="text-center">
            <button className="btn btn-success" onClick={
                ()=>navigate(`/boards/form/${state.id}`,{state})
                
                     }>수정</button>
            <button className="btn btn-success mx-2" onClick={
                ()=>{
                    //JSX에서 confirm()을 사용시에는 반드시 앞에 window객체를 붙인다
                    if(window.confirm('정말로 삭제 하시겠습니까?')){
                        handleBoardDelete(state.id);
                    }
                }
            }>삭제</button>

            <Link to="/boards" className="btn btn-success">
            목록
            </Link>
        </div>
        
        </>
    );
};

export default View;
