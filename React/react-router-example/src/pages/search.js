import { useLocation, useSearchParams } from "react-router-dom";

const Search = ()=>{
    //쿼리 스트링은 useLocation()훅 함수로 받는다
    const location=useLocation();
    console.log('location:',location);
    console.log('쿼리 스트링:',location.search);
    const {pathname,search,key,state} = location;
    //state는 쿼리스트링을 받을때는 null이다
    //단,<Route/>컴포넌트가 아닌 
    //useNavigate("/URL패턴",{state:props혹은 state값})로 
    //컴포넌트 전환(이동)시에는 state키에 지정한 값이 된다
    console.log('pathname:%s,search:%s,key:%s,state',pathname,search,key,state);
    //쿼리스트링을 파싱 하기위해 별도의 라이브러리가 필요없다(v6에서)
    //urlSearchParams는 해당 컴포넌트(Search)의 state다
    //즉 검색수 변경을 위한 별도의 state가 필요없다
    const searchParams=useSearchParams();
    console.log('searchParams:',searchParams);
    //urlSearchParams는 state,setSearchParams는 세터다
    const [urlSearchParams,setSearchParams]= searchParams;
    const keyword = urlSearchParams.get('keyword');
    const searchCount = urlSearchParams.get('searchCount');
    
    const prevState= [...urlSearchParams].map(([key,value])=>({[key]:value})).reduce((acc, obj) => ({...acc, ...obj}));
    console.log('prevState:',prevState);
    return <>
        <h2>쿼리 스트링</h2>
        <p>키워드 : {keyword}</p>
        <p>검색 수 : {searchCount}</p>
        <button className="btn btn-info" onClick={()=>{
            const nextCount = searchCount==null ? 1 : parseInt(searchCount)+1;           
            setSearchParams({...prevState,searchCount:nextCount});
            
        }} >검색수 증가</button>
    </>
};

export default Search;