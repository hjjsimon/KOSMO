import { useOutletContext,useParams } from "react-router-dom";

const UserProfile = ()=>{
  const {user}=useOutletContext();
  return <>
    <h2>{user.username}의 상세 정보</h2>
    <table className="table table-bordered"> 
        <tbody>   
          <tr>
            <th className='w-25 bg-dark text-white text-center'>아이디</th>
            <td>{user.username}</td>
          </tr>
          <tr>
            <th className='w-25 bg-dark text-white text-center'>이 름</th>
            <td>{user.name}</td>
          </tr>
          <tr >
            <th className='w-25 bg-dark text-white text-center'>프로필</th>
            <td>{user.profile}</td>
          </tr>
        </tbody>  
      </table>
  </>
};

//B.별도의 라우터로 URL파라미터 구성시
/*
const UserProfile = ({users})=>{  
  const params=useParams();
  console.log('UserProfile의 params:',params);//{id:클릭한 사용자 아이디값}
  const user = users.filter(user=>user.id===params.id)[0];
  return <>
    <h2>{user.id}의 상세 정보</h2>
    <table className="table table-bordered">
        <tr>
          <th className='w-25 bg-dark text-white text-center'>아이디</th>
          <td>{user.id}</td>
        </tr>
        <tr>
          <th className='w-25 bg-dark text-white text-center'>이 름</th>
          <td>{user.name}</td>
        </tr>
        <tr >
          <th className='w-25 bg-dark text-white text-center'>프로필</th>
          <td>{user.profile}</td>
        </tr>
      </table>
  </>
};
*/
export default UserProfile;