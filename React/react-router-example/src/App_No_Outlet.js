import { Route, Routes } from "react-router-dom";
import Header from "./pages/header";
import Home from "./pages/home";
import About from "./pages/about";
import NotFound from "./pages/not_found";
import UserProfile from "./pages/userProfile";
import Users from "./pages/usres";

//더미 데이타
const users = [
  {  
    id:"kim",
    name: '김길동',
    profile: '김길동입니다',
  },
 {  id:"lee",
    name: '이길동',
    profile: '이길동입니다',
  },
  {
    id:"park",
    name: '박길동',
    profile: '박길동입니다',
  },
  {
    id:"choi",
    name: '최길동',
    profile: '최길동입니다',
  },
  {
    id:"kosmo1",
    name: '한소인1',
    profile: '한소인1입니다',
  },
  {
    id:"kosmo2",
    name: '한소인2',
    profile: '한소인2입니다',
  },
  {
    id:"kosmo3",
    name: '한소인3',
    profile: '한소인3입니다',
  }, 
];

{/*Outlet 컴폰너트 미 사용*/}
function AppNoOutlet() {
  return <>
    {/*
      첫번째:<Routes>밖에 배치(Header가 Routes의 영향을 받지 않아 URL에따라 
      라우팅을 적용받지 않는다
    */}
    <Header/>   
    <div className="container">
      {/* 화면용 뷰인 각 컴포넌트로 라우팅*/}
      <Routes>
         {/*
        -<Routes>안에 <Header/>포함시
        [Header] is not a <Route> component. All component children of <Routes> must be a <Route> or <React.Fragment>
        <Header/>   
        */}    
        {/*<Header/>*/}
        {/*
          특정 URL로 라우팅 하기
          <Route path="URL패턴" element={화면용 컴포넌트} />
          URL패턴은 /로 시작한다.
          단 서브 라우트 구현시에는 /를 생략하여 부모 URL를 기준으로 한다*/}
        
        <Route path="/" element={<Home/>}/>{/*Route는 반드시 Routes안에 있어야 한다*/}
        <Route path="/about" element={<About/>}/>
        {/*중첩 라우팅(<Route>안에 <Route>)으로 URL파라미터 설정*/}  
        
        <Route path="/users" element={<Users users={users}/>}>
          {/*
          A.서브 라우터로 URL파라미터 구성시
          - "/uses/아이디값" URL요청을 해도 뷰는 Users컴포넌트다
          - URL파라미터는 부모와 자식 둘다에서 받을 수 있다          
          - 중첩 라우팅은 부모 컴포넌트(뷰)에서 자식 컴포넌트(뷰)를 
            보여주고자 할때 사용한다 
          - 모든 사용자 데이타를 자식인 UserProfile에 속성으로 내릴 필요 없다      
          */}      
          <Route path=":id" element={UserProfile}/>
        </Route>
        {/*   
          B.별도의 라우터로 URL파라미터 구성시
          - "/uses/아이디값" URL요청시 뷰는 UserProfile컴포넌트다
          - URL파라미터는  UserProfile컴포넌트에서 받아야한다  
          - 별도의 화면으로 자식 컴포넌트(뷰)를 보여주고자 할때 사용한다 
            즉 UserProfile뷰를 독립된 화면으로 보여줄때
          - 모든 사용자 데이타를 속성으로 내려야 한다   
        */}
        {/*<Route path="/users/:id" element={<UserProfile users={users}/>}/>*/}
        <Route path="*" element={<NotFound/>}/>
      </Routes>
    </div>
  </>
}

export default AppNoOutlet;
