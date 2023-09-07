import { useEffect, useReducer, useState } from "react";
import { Route, Routes, useNavigate } from "react-router-dom";
import Template from "./pages/template";
import Home from "./pages/home";
import Login from "./pages/login";
import axios from "axios";
import Users from "./pages/users";
import  { fetchReducer } from "./hooks/useDataFetch";
import UserProfile from "./pages/userProfile";
import NotFound from "./pages/not_found";
import BoardsPaging from "./pages/boards_paging";
import Photos from "./pages/photos";
import usersReducer from "./reducers/users_reducer";
import { UsersContext } from "./context/users_context";

//State 관리를 useState() 훅 함수에서  useReducer() 훅으로 변경하자
const initialState={
  isLogin:null,//로그인한 사용자 아이디 저장
  users:[],//모든 사용자 목록 저장
  error:null//에러 저장
}

function App() {

  //사용자 데이타를 State로 관리하기위한 리듀서 생성
  const [usersInfo,dispatch] = useReducer(usersReducer,initialState);

  //로그인 후 페이지 이동 처리(useNavigate의 함수를 사용)를 위한 useNavigate 생성
  const navigate=useNavigate();

  //1.모든 사용자 목록 가져오기-마운트 된후 한번만 호출:의존성 배열 []
  useEffect(()=>{
    console.log('App의 useEffect콜백함수');
    fetchReducer('http://localhost:3002/users')
    .then(res=>dispatch({type:'all',users:res.data,isLogin:sessionStorage.getItem("username")}))
    .catch(e=>dispatch({type:'error',error:e}));
  },[]);
  
 



  //2-1.로그인 처리 함수-Login컴포넌트로 Props으로 내려준다(Outlet를 사용함으로 Props로 내릴필요없다).
  //username,password인자는 Login컴포넌트에서 입력한 값을 받는다
  const handleLoginProcess=(username,password)=>{
    axios.get('http://localhost:3002/users',{params:{username,password}})
         .then(function(response){           
            console.log('로그인 처리 성공:',response);
            if(response.data.length !=0){//회원인 경우
              //로그인 처리-세션 스토리지에 'username'키로 저장
              sessionStorage.setItem('username',username);
              //로그인 상태 변경              
              dispatch({type:'login',isLogin:username});
              //해당 아이디의 상세 프로필로 이동
              navigate(`/users/${username}`);
            }
            else{//회원이 아닌 경우
              alert('아이디와 비번 불일치...');
            }

         })
         .catch(function(error){
            dispatch({type:'error',error});
         })

  };
  //2-2.로그아웃 처리 함수-Header컴포넌트까지 내려준다
  const handleLogout = ()=>{
    //세션 스토리지에 "username"이라는 키 삭제
    sessionStorage.removeItem('username');
    //로그인 상태 null로 변경
    dispatch({type:'logout'});
  };
  console.log('App렌더링....');
  return <>

  <UsersContext.Provider value={{usersInfo,handleLoginProcess,handleLogout}}>
      <Routes>
        <Route element={<Template/>}>
          <Route path="/" element={<Home/>}/>
          <Route path="/login" element={<Login/>}/>
          <Route path="/users" element={<Users/>}>
            <Route path=":username" element={<UserProfile/>}/>
          </Route>
          {/*App의 하위 컴포넌트에서 라우팅:Boards의 하위 Route가 있다면(즉 Boards에서 라우팅을 한다면) 상위 Route에 '/*' 을 추가*/}
          {/*
          페이징 미 적용 버전
          <Route path="/boards/*" element={<Boards/>}/>
          */}
          {/* 페이징 적용 버전*/}
          <Route path="/boards/*" element={<BoardsPaging/>}/>
          <Route path="/photos" element={<Photos/>}/>
          <Route path="*" element={<NotFound/>}/>    
        </Route>     
      </Routes> 
    </UsersContext.Provider> 
  </>;
}

export default App;
