import { Outlet, useParams } from "react-router-dom";
import UserProfile from "./userProfile";
import User from "./user";
//좋아요 구현시 updateLikes 추가
export default function Users({users,updateLikes}){//사용자 목록을 출력 해야함으로 사용자 전체 데이타를 PROS로 내려받는다
    //A.중첩 라우터로 URL파라미터 구성시
    //URL파라미터를 받은  라우터의 훅 함수:useParams().{URL파라미터명:파라미터값} 반환
    const params=useParams();
    console.log('Users의 URL 파라미터:',params);//{id:'kim'}
    //URL파라미터(params)의 사용자 아이디 체크
    const user = params.id ? users.filter(user=>user.id===params.id)[0] : null;
    console.log('사용자 아이디:',user);
    
    //[아래처럼 UI구성]
    //   /users/kim 일때 사용자 목록위에 <UserProfile/> 추가
    //  /users 일때는 사용자 목록
    return <>
       {/* {user ? <UserProfile user={user}/> : null}  혹은  */}
           
       {/* App_No_Outlet.js 컴포턴트로 렌더링시 */}
       {/*별도의 사용자 프로필 화면으로 보여줄때는 아래 주석처리*/}   
       {/*{user && <UserProfile user={user}/>} */}
       {/* App_Outlet.js 컴포턴트로 렌더링시 
           위처럼 <UserProfile user={user}/> PROPS를 내릴 필요 없다
       */}
       {user && <Outlet context={{user}}/>}

       <div className="row">
            {/* 좋아요 구현시 updateLikes={updateLikes} 추가*/}
            {users.map(user=>{return (<div className="col-sm-3" key={user.id}><User user={user} updateLikes={updateLikes}/></div>)})}
       </div>
    </>
}