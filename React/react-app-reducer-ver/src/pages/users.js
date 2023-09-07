import { Outlet, useOutletContext, useParams } from "react-router-dom";
import User from "./user";
import { useContext } from "react";
import { UsersContext } from "../context/users_context";

export default function Users(){
    
    const {usersInfo}=useContext(UsersContext);
    console.log('Users의 usersInfo:',usersInfo);
    const params=useParams(); 
    const {users} = usersInfo;//구조분해해서 users만 사용

    const user = params.username ? users.filter(user=>user.username===params.username)[0] : null;
   
    console.log('(Users.js)의 users:',users);
    return <>
      
       {user && <Outlet context={{user}}/>}{/*UserProfile컴포넌트 표시*/}
       <div className="row">            
            {users.map(user=>{return (<div className="col-sm-3" key={user.username}><User user={user} /></div>)})}
       </div>
    </>
}