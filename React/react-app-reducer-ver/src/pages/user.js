import { Link } from "react-router-dom";

export default function User({user}){

    return <>
        <div className="card" >
            <img className="card-img-top" src="/images/img_avatar1.png" alt="Card image"/>
            <div className="card-body">
                <h4 className="card-title">{user.name}</h4>
                <p className="card-text">{user.profile}</p>
               
                <Link to={user.username} className="btn btn-primary">프로필</Link>               
               
            </div>
        </div>       
    
    </>
}