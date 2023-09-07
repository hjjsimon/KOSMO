import { Route, Routes } from "react-router-dom";
import Header from "./pages/header";
import Home from "./pages/home";
import About from "./pages/about";
import NotFound from "./pages/not_found";
import UserProfile from "./pages/userProfile";
import Users from "./pages/usres";
import Template from "./pages/template";
import Search from "./pages/search";
import Navigate from "./pages/navigate";
import { useState } from "react";


/*
  "likes" key 추가전에는 굳이 사용자 데이타를 State로 관리할 필요 없다
   왜냐하면 데이타 변경이 필요 없으니까
   하지만 "likes" 데이타는 자식(User)에서 변경한다
   변경하는 데이타가 있으면 무조건 state로 관리해야 한다.

*/

//더미 데이타
const users = [
  {  
    id:"kim",
    name: '김길동',
    profile: '김길동입니다',
    likes:0,

  },
 {  id:"lee",
    name: '이길동',
    profile: '이길동입니다',
    likes:0,
  },
  {
    id:"park",
    name: '박길동',
    profile: '박길동입니다',
    likes:0,
  },
  {
    id:"choi",
    name: '최길동',
    profile: '최길동입니다',
    likes:0,
  },
  {
    id:"kosmo1",
    name: '한소인1',
    profile: '한소인1입니다',
    likes:0,
  },
  {
    id:"kosmo2",
    name: '한소인2',
    profile: '한소인2입니다',
    likes:0,
  },
  {
    id:"kosmo3",
    name: '한소인3',
    profile: '한소인3입니다',
    likes:0,
  }, 
];

/*중첩 라우팅 구현시-Outlet 컴포넌트 사용
모든 자식이 부모의 context속성에 지정한 데이타를
useOutletContext()훅 함수를 호출해서 가져다 쓸수 있다
따라서 자식 컴포넌트로 데이타를 속성으로 내릴 필요없다
*/
function AppOutlet() {

  //사용자 데이타에 "likes"키 추가시 코드 추가 시작
  //1.사용자 데이타 State로 설정
  const [allUsers,setAllUsers] = useState(users);
  //2.좋아요 수정 함수정의 -User컴포넌트로 내린다.User컴포넌트에서는 id를 넣어서 호출한다
  const updateLikes=(id)=>{//
    console.log('수정할 사용자 아이디:',id);
    //map()함수로 순회하면서 키로 찾아 수정
    const newState = allUsers.map((user)=>user.id===id?{...user,likes:user.likes+1}:user);
    
    setAllUsers(newState);
  };

  console.log('최상위 컴포넌트 렌더링....');
  return <> 
      <Routes>
        {/*Outlet 사용해서 모든 뷰(화면)에 공통으로 보여지는 컴포넌트(Header) 설정  */} 
        <Route element={<Template/>}>{/* path속성은 미 지정*/}   
          <Route path="/" element={<Home/>}/>
          <Route path="/about" element={<About/>}/>        
          {/* 좋아요 구현시 updateLikes={updateLikes}추가 */}
          <Route path="/users" element={<Users users={allUsers} updateLikes={updateLikes}/>}>  
            {/*반드시 컴포넌트는 < />로 감싸자
            Warning: Functions are not valid as a React child. This may happen if you return a Component instead of <Component /> from render. Or maybe you meant to call this function rather than return it.
            
            */}        
            <Route path=":id" element={<UserProfile/>}/>
          </Route> 
          <Route path="/search" element={<Search />}/>   
          <Route path="/navigate" element={<Navigate/>}/>         
          <Route path="*" element={<NotFound/>}/>
        </Route>    
      </Routes>
    </>
}

export default AppOutlet;
