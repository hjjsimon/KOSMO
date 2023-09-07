
import { Link,NavLink, useNavigate } from "react-router-dom";

export default function Header({isLogin,handleLogout}){
  
  //NavLink에 style속성으로 설정시
  const activeStyle={color:'#90EE90',fontWeight:'bold'};
  
  const navigate=useNavigate();
  return <>
   
    <nav className="navbar navbar-expand-sm bg-secondary navbar-dark fixed-top">
      
      <NavLink className="navbar-brand" to="/" style={({isActive})=>isActive?activeStyle:null}>HOME</NavLink>
      
      
       <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-link">
         <span className="navbar-toggler-icon"></span>
       </button>
 
       <div className="collapse navbar-collapse justify-content-end" id="navbar-link">
         <ul className="navbar-nav">
           <li className="nav-item">             
              <NavLink className="nav-link" to="/users" style={({isActive})=>isActive?activeStyle:undefined}>회원</NavLink>       
           </li>
           <li className="nav-item">    
            {/* 로그아웃클릭시 이벤트 처리후 to속성에 지정한 URL로 이동한다*/}        
            {!isLogin ?
            <Link className="nav-link" to="/login">로그인</Link>  
            :
            
            <Link className="nav-link" to="/" onClick={()=>{handleLogout();}}>로그아웃</Link>     
            }
          </li>           
           <li className="nav-item">           
             <NavLink className="nav-link" to={isLogin?"/boards":"/login"} onClick={()=>{!isLogin && alert('로그인후 이용하세요')}} style={({isActive})=>isActive?activeStyle:undefined}>게시판</NavLink>                
           </li>
   
           <li className="nav-item">            
             <NavLink className="nav-link" to="/photos" style={({isActive})=>isActive?activeStyle:undefined}>사진앨범</NavLink>
           </li>
          
         </ul>
       </div>       
     </nav>  
  </>
}