import { Link, NavLink, useNavigate } from "react-router-dom";

export default function Header(){

    //NavLink에 style속성으로 설정시
    const activeStyle ={color:'#00FF00',fontWeight:'bold'};
    
    return <>
        <nav className="navbar navbar-expand-sm bg-secondary navbar-dark fixed-top">
            {/*<a className="navbar-brand" href="/">HOME</a>*/}
            {/*<Link className="navbar-brand" to="/">HOME</Link>*/}
            {/* NavLink클릭시 {isActive:boolean}형태의 객체를 NavLink컴포넌트가 받는다 
                즉 반드시 isActive라는 키로 값을 받아야 style이 적용된다
            */}
            {/*style지정시 undefined나 null로 지정하면 style 속성값이 표시안됨*/}
            <NavLink className="navbar-brand" to="/" style={({isActive})=>isActive?activeStyle:null}>HOME</NavLink>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-link">
                <span className="navbar-toggler-icon"></span>
            </button>
            <div className="collapse navbar-collapse justify-content-end" id="navbar-link">
                <ul className="navbar-nav">
                    <li className="nav-item">
                        {/*<a className="nav-link" href="#">회원</a>*/}
                        <NavLink className="navbar-brand" to="/users" style={({isActive})=>isActive?activeStyle:undefined}>회원</NavLink>
                    </li>
                    <li className="nav-item">
                        {/*<a className="nav-link">회사소개</a>*/}
                        <NavLink className="navbar-brand" to="/about" style={({isActive})=>isActive?activeStyle:undefined}>회사소개</NavLink>
                    </li>
                    <li className="nav-item">
                        {/*<a className="nav-link" href="#">퀴리스트링</a>*/}
                        <NavLink className="navbar-brand" to="/search?keyword=react&searchCount=10" style={({isActive})=>isActive?activeStyle:undefined}>쿼리스트링</NavLink>
                    </li>
                    <li className="nav-item">
                        {/*<a className="nav-link" href="#">Navigate</a>*/}
                        <NavLink className="navbar-brand" to="/navigate" style={({isActive})=>isActive?activeStyle:undefined}>Navigate</NavLink>
                    </li>
           {/* navigate(음수 혹은 양수);  history.go(음수 혹은 양수)와 같다*/}
                    <li className="nav-item">
                        <button  className="btn btn-info">Back</button>
                    </li>
                </ul>
            </div>
        </nav>
    
    </>

}