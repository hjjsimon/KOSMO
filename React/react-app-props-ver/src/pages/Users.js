import { Outlet,  useLocation,  useParams } from "react-router-dom";
import User from "./User";
import UserProfile from "./UserProfile";

export default function Users({users}){
  const params =useParams();
  const user = params.username ? users.filter(user=>user.username===params.username)[0]:null;

  return <>
    {user && <Outlet context={{user}}/> }
    <div className="row">
      {users.map((user)=>{return (<div className="col-sm-3" key={user.username}><User   user={user}/></div>)})}              
    </div>
  </> 

}