import { useNavigate } from "react-router-dom"

export default function Navigate(){
    //<Route>컴포넌트가 아닌 함수 호출로 페이지 전환시(컴포넌트 전환시) 사용하는 훅함수
    const navigate=useNavigate(); //함수 반환
    console.log('navigate:',navigate);

    return <>
        {/* navigate('URL패턴');  location.href와 같다*/}
        {/*
            Home ->AboutUs(회사소개)-> Naviagte에서
            아래 NO HISTORY(Users) 클릭					 
            탑 메뉴에 있는 BACK 버튼을 눌러 이전 페이지로 이동
            만약 { replace: true } 설정이 없었더라면 
            직전에 봤던 페이지인 Navigate 페이지가 나타나야 하지만,
            이 옵션 때문에 Users이 스택이 없어서 Navigate가 현재 페이지가 되어
            AboutUs 페이지가 나타나게 된다
      */}
        <button className="btn btn-success" onClick={()=>navigate('/users',{replace:true})}>NO HISTORY</button>
         {/* navigate('/URL패턴',{state:데이타}) URL패턴에 매핑된 컴포넌트로 데이타 전달시   */}
        <button className="btn btn-danger mx-1" onClick={()=>navigate('/search?searchCount=1&keyword=useNavigate',{state:{name:'한소인'}})} >DATA TRANSFER</button>
    </>
}