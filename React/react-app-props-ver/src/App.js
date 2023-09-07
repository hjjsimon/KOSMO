import Home from "./pages/Home";
import NotFound from "./pages/NotFound";
import Users from "./pages/Users";
import UserProfile from "./pages/UserProfile";
import { Routes,Route, useNavigate } from "react-router-dom";
import Template from "./pages/Template";
import useFetch from "./hooks/useFetch";
import {  useState } from "react";
import Login from "./pages/Login";
import axios from "axios";
import Boards from "./pages/bbs/Boards";
import Photos from "./pages/Photos";

function App() {

  //로그인후 페이지 이동 처리를 위한 훅 함수객체 생성
  const navigate=useNavigate();
  //1.모든 사용자 목록 가져오기:[상태값,세터]형태이다
  const users=useFetch('http://localhost:3002/users',[])[0];
  console.log('(App.js)사용자 목록:',users);
  /*
  (App.js)사용자 목록: [] :App()이 처음 호출되었을때
  (App.js)사용자 목록: (3) [{…}, {…}, {…}] : 서버로부터 데이타를 받아 State가 변경되서 리렌더링 즉 App()다시 호출되서
  */
  //2.로그인 상태를 Application State로 관리 - 세션스토리지에서 로그인 정보 읽기
  //  즉 로그인 상태가 필요한 모든 하위컴포넌트로 Props로 내린다
  //  Local State로 관리하면 안됨 즉 각 컴포넌트마다 로그인 상태가 다르다는 의미임으로..
  const [isLogin,setIsLogin]= useState(sessionStorage.getItem('username'));
  console.log('isLogin:',isLogin);
  //2-1.로그인 처리 함수-Login컴포넌트로 Props으로 내려준다.
  //username,password인자는 Login컴포넌트에서 입력한 값을 받는다
  const handleLoginProcess=(username,password)=>{
    //반드시 get방식으로 요청
    axios.get('http://localhost:3002/users',{params:{
      username,password}})
    .then(function (response) {
      console.log('로그인 처리 성공:',response);
      //회원인 경우:[{username:아이디,....}]
      //회원이 아닌경우:[]
      if(response.data.length!=0){//회원인 경우
          //세션 스토리지에 "username"이라는 키로 아이디 저장
          sessionStorage.setItem('username',username);
          //로그인 상태 변경
          setIsLogin(username);
          //해당 아이디의 상세 프로필로 이동
          navigate(`/users/${username}`);
      }
      else{//회원이 아닌 경우
        alert('아이디와 비번이 일치하지 않아요');
      }
    })
    .catch(function (error) {
      console.log(error);
    });
  };
  //2-2.로그아웃 처리 함수-Header컴포넌트까지 내려준다
  const handleLogout=()=>{
    //세션 스토리지에 "username"이라는 키 삭제
    sessionStorage.removeItem('username');
    //로그인 상태 null로 변경
    setIsLogin(null);
  };
  console.log('App렌더링')
  return <>

    <Routes>
      <Route element={<Template isLogin={isLogin} handleLoginProcess={handleLoginProcess} handleLogout={handleLogout}/>}>
         
        <Route path="/" element={<Home/>}/>         
        <Route path="/users" element={<Users users={users}/>}>          
          <Route path=":username" element={<UserProfile/>}/>
        </Route> 
        <Route path="/login" element={<Login/>}/>
        
        {/*App의 하위 컴포넌트에서 라우팅:Boards의 하위 Route가 있다면(즉 Boards에서 라우팅을 한다면) 상위 Route에 '/*' 을 추가*/}
        {/*단,App에서 모두 라우팅을 한다면 Boards불필요*/}
        <Route path="/boards/*" element={<Boards/>}/>
        <Route path="/photos" element={<Photos/>}/>
        <Route path="*" element={<NotFound/>}/> 
      </Route>
    </Routes>

  </>
};

export default App;
