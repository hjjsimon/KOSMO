import { useRef, useState } from "react";
import { useLocation } from "react-router-dom";

const Form = ({handleBoardCreate,handleBoardUpdate})=>{

  //하나의 폼 컴포넌트를 등록과 수정폼으로 사용
  //각 버튼(등록/수정)클릭시 전달된 state로 수정인지 등록인지 판단
  const {state}=useLocation();//useNavigate()객체로 이동시 state를 받기 위한 훅 함수
  //console.log('Form의 state:',state);  
  //폼 요소 제어용 Ref객체 생성
  const titleRef = useRef(null);  
  const contentRef = useRef(null);


  //유효성 체크-span에 메시지로 표시할때 추가 코드 시작
  //선행작업:span요소 추가 및 각 입력요소에 onChange이벤트 추가
  //유효성 체크 메시지 출력용 span요소 제어용 Ref객체 생성
  const titleSpanRef = useRef(null);
  const contentSpanRef = useRef(null);
  //유효성 체크시 메시지 출력을 위해 span요소의 컨텐츠를 State로 관리
  const [titleMessage,setTitleMessage]=useState(''); 
  const [contentMessage,setContentMessage]=useState('');  
  //유효성 체크-span에 메시지로 표시할때 추가 코드 끝

  //버튼 이벤트 처리
  const handleActicle = (e)=>{
      e.preventDefault();//버튼의 제출기능 막기 즉 새로고침 막기
      if(titleRef.current.value===''){
        //alert('제목을 입력하세요');//유효성 체크-alert()함수로 할때
        setTitleMessage('제목을 입력하세요');//유효성 체크-span에 메시지로 표시할때
        titleRef.current.focus();
        
      }
      if(contentRef.current.value===''){
        //alert('내용을 입력하세요');//유효성 체크-alert()함수로 할때
        setContentMessage('내용을 입력하세요');//유효성 체크-span에 메시지로 표시할때
        contentRef.current.focus();        
      }
      if(titleRef.current.value==='' || contentRef.current.value==='') return;
      //state에 따라 등록용 함수 혹은 수정용 함수를 호출
      state==='CREATE'? handleBoardCreate(titleRef.current.value,contentRef.current.value) : handleBoardUpdate({...state,title:titleRef.current.value,content:contentRef.current.value});
  };

  //※폼의 입력요소에 value속성을 추가(수정으로 인한 추가-작성내용 미리 보여주려고)하면 읽기전용 속성으로 변한다
  //  하나의 폼을 등록 및 수정폼으로 사용하고 있다 즉 등록시 텍스트 입력이 안된다
  //  해결책:
  //  1)onChange속성 추가 및 name속성 추가
  //  2)입력요소의 값들을 State로 관리 - 
  //    등록시는 초기값을 제목과 내용을 ''로 
  //    수정시는 제목을 state.title 내용은 state.content 
  //  3)onChange 이벤트 안에서 입력요소값을 세터로 변경

  const [content,setContent] = useState(()=>({title:state==='CRETAE'?'':state.title,content:state==='CREATE'?'':state.content,isMount:true}),[]);
  //수정시 value속성값을 마운트시에는 state.title 값으로 리렌더링시에는 content.title로 설정해야 한다.
  
  return <>
    <div className="jumbotron">
      <h1>게시판 <small>{state==='CREATE' ?"등록":'수정'} 페이지</small></h1>
    </div>
    <form>
      <div className="form-group">
        <label><kbd className="lead">제목</kbd></label>
        {/*
        value속성은 수정을 위한 추가
        
        */}
        <input        
        value={state!='CREATE' ? content.isMount ? state.title : content.title : content.title} 
        onChange={(e)=>{ 
          console.log('제목입력창:',e.target.value)
          //span에 유효성 메시지 출력코드
          e.target.value==0?
          setTitleMessage('제목을 입력하세요'):
          setTitleMessage('');

          //입력요소값을 State로 설정시 추가 코드
          //새로운 객체를 직접 인자로 전달해서 변경시 - 연속호출시 isMount변경이 안된다
          //setContent({...content,title:e.target.value});
          //setContent({...content,isMount:false});
          
          //isMount속성 변경 해결책
          //방법1
          //인자로 새로운 객체가 아닌 이전 State를 인자로 갖는 콜백함수를 넣어주면 변경된다
          //setContent((prevState)=>({...prevState,title:e.target.value}))
          //setContent((prevState)=>({...prevState,isMount:false}))
          //방법2
          //setContent({...content,title:e.target.value,isMount:false});
          
          setContent((prevState)=>({...prevState,title:e.target.value,isMount:false}));
        }
        } 
        ref={titleRef}  type="text" className="form-control" placeholder="제목을 입력하세요" name="title" />
      
        <span style={{color:'#ff0000'}} ref={titleSpanRef}>{titleMessage}</span>
      </div>
      <div className="form-group">
        <label><kbd className="lead">내용</kbd></label>
        {/* JSX에서는 textarea의 컨텐츠를 value속성으로 설정 */}
        <textarea 
        value={state!='CREATE' ? content.isMount ? state.content:content.content : content.content}   
        onChange={(e)=>{
          //span에 유효성 메시지 출력코드
          e.target.value==0 ? 
          setContentMessage('내용을 입력하세요') : 
          setContentMessage('');

          //입력요소값을 State로 설정시 추가 코드
          setContent((prevState)=>({...prevState,content:e.target.value,isMount:false}));
          
         }} ref={contentRef}  className="form-control" rows="5" name="content" placeholder="내용을 입력하세요"></textarea>
        <span style={{color:'#ff0000'}} ref={contentSpanRef}>{contentMessage}</span>
      </div>
      <button className="btn btn-primary" onClick={handleActicle}>{state==='CREATE' ?"등록":'수정'}</button>
    </form>
  </>
};
export default Form;