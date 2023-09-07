import { useContext } from "react";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { UsersContext } from "../context/users_context";

export default function Header(){

    const {usersInfo,handleLogout} = useContext(UsersContext);
    const {isLogin} = usersInfo;
    console.log('헤더의 isLogin:',isLogin);
    //NavLink에 style속성으로 설정시
    const activeStyle ={color:'#90EE90',fontWeight:'bold'};
    
    //메뉴의 클릭 이벤트 발생후 to속성에 지정한 URL로 이동한다
    return <>
        <nav className="navbar navbar-expand-sm bg-secondary navbar-dark fixed-top">
            
            <NavLink className="navbar-brand" to="/" style={({isActive})=>isActive?activeStyle:null}>HOME</NavLink>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-link">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse justify-content-end" id="navbar-link">
                <ul className="navbar-nav">
                    
                    <li className="nav-item">  
                        {/*로그인되면 로그인 메뉴는 안보이기 때문에 style은 의미 없다*/}   
                        {/*로그아웃 클릭시 이벤트 처리후 to속성에 지정한 URL로 이동한다*/}         
                        {!isLogin ?
                        <NavLink className="navbar-brand" to="/login" >로그인</NavLink>
                         : 
                        <NavLink className="navbar-brand" to="/" onClick={()=>handleLogout()} >로그아웃</NavLink> 
                         }                 
                        
                    </li>
                    <li className="nav-item">                       
                        <NavLink className="navbar-brand" to="/users" style={({isActive})=>isActive?activeStyle:undefined}>회원</NavLink>
                    </li>
                    <li className="nav-item">  
                             
                    <NavLink className="navbar-brand" to={isLogin?'/boards':'/login'} onClick={()=>{!isLogin && alert('로그인후 이용하세요')}} style={({isActive})=>isActive?activeStyle:undefined}>게시판</NavLink>
                    </li>
                    <li className="nav-item">                        
                        <NavLink className="navbar-brand" to="/photos" style={({isActive})=>isActive?activeStyle:undefined}>사진앨범</NavLink>
                    </li>
           
                </ul>
            </div>
        </nav>
    
    </>

}