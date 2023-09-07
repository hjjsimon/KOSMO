import { useOutletContext, useParams } from "react-router-dom";

const UserProfile =()=>{//App_Outlet.js로 렌더링시
//const UserProfile =({user})=>{//A.중첩 라우터로 URL파라미터 구성시
//const UserProfile =({users})=>{//B.별도의 라우터로 URL파라미터 구성시
    console.log('UserProfile렌더링');    
    //URL파라미터를 받은  라우터의 훅 함수:useParams().{URL파라미터명:파라미터값} 반환
    const params=useParams();
    console.log('UserProfile의 URL 파라미터:',params);

    //B.별도의 라우터로 URL파라미터 구성시 아래 코드 추가
    //const user = users.filter(user=>user.id===params.id)[0];

    //App_Outlet.js로 렌더링시
    const {user}=useOutletContext();
    console.log('App_Outlet.js로 렌더링시 user:',user);
    return <>
        <h2>{user.name}의 상세 정보</h2>
        <table className="table table-bordered">
        <tbody>
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
        </tbody>
      </table>    
    </>
};
export default UserProfile;