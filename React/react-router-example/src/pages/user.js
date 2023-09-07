import { Link } from "react-router-dom";

//좋아요 구현시 updateLikes 추가
export default function User({user,updateLikes}){

    return <>
        <div className="card" >
            <img className="card-img-top" src="/images/img_avatar1.png" alt="Card image"/>
            <div className="card-body">
                <h4 className="card-title">{user.name}</h4>
                <p className="card-text">{user.profile}</p>
                {/*
                    -상대경로
                    라우팅된 현재 URL 화면(http://localhost:3000/users)의 컴포넌트(Users)에서
                    하위 컴포넌트(User)의 JSX에서 Link계열 컴포넌트 to속성에 상대경로를 지정하면
                    현재 URL과 결합된다
                    예: <Link to="kosmo" ~
                    <Link는 아래와 같이 렌더링 된다
                    <a href="/users/kosmo" ~
                */}
                <Link to={user.id} className="btn btn-primary">프로필</Link>
                {/* -절대경로*/}
                {/*<Link to={"/users/"+user.id} className="btn btn-primary">프로필</Link>*/}
                {/*마지막에 구현:좋아요 기능*/}
                <img onClick={()=>{console.log('하트클릭');updateLikes(user.id);}} style={{width:"20px",height:"20px",marginLeft:"50px"}} src="/images/heart_icon.png" alt="좋아요" />
                &nbsp;{user.likes}
            </div>
        </div>       
    
    </>
}