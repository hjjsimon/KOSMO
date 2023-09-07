import { useRef } from "react"
import { useOutletContext } from "react-router-dom";

export default function Login(){

  //입력요소를 Ref객체로 접근-굳이 State로 관리 불 필요 
  const usernameRef = useRef(null);//{current:null}
  const passwordRef = useRef(null);

  const {handleLoginProcess}=useOutletContext();
  //로그인 버튼 이벤트 처리
  const handleLogin =(e)=>{
    //버튼의 기본적인 기능인 submit기능 막기
    e.preventDefault();
    //App컴포넌트에 로그인 처리 요청하도록 함수 호출
    handleLoginProcess(usernameRef.current.value,passwordRef.current.value);
  };
  return <>
    <div className="jumbotron">
	    <h1>로그인</h1>	     
	  </div>
    <form className="form-inline">
			<label>아이디</label>
      <input ref={usernameRef}  type="text" name="username"	className="form-control mx-2" />
      <label>비밀번호</label>
      <input ref={passwordRef} type="password" name="password" className="form-control mx-2" />
      <button  className="btn btn-danger mx-2" onClick={handleLogin}>로그인</button>
		</form>
  
  </>
}