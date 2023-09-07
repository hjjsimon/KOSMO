import { useContext, useRef, useState } from "react";
import { useLocation } from "react-router-dom";
import { BoardsContext } from "../../context/bbs_context";

const Form = () => {
  
  
  const {handleBoardCreate} = useContext(BoardsContext);
  //폼 요소 제어용 Ref객체 생성
  const titleRef = useRef(null);
  const contentRef = useRef(null);
  //유효성 체크 메시지 출력용 span요소 제어용 Ref객체 생성
  const titleSpanRef = useRef(null);
  const contentSpanRef = useRef(null);

  //유효성 체크-span에 메시지로 표시할때 추가 코드 시작
  //선행작업:span요소 추가 및 각 입력요소에 onChange이벤트 추가
  //유효성 체크시 메시지 출력을 위해 span요소의 컨텐츠를 State로 관리
  const [titleMessage,setTitleMessage]= useState('');
  const [contentMessage,setContentMessage]= useState('');
  //유효성 체크-span에 메시지로 표시할때 추가 코드 끝


  //등록/수정 버튼 이벤트 처리
  const handleArticle = (e)=>{
      
      e.preventDefault();//버튼의 제출기능 막기 즉 새로고침 막기
      if(titleRef.current.value===''){
        //alert('제목을 입력하세요');
        setTitleMessage('제목을 입력하세요');//span에 메시지로 표시할때
        titleRef.current.focus();
        
      }
      if(contentRef.current.value===''){
        //alert('내용을 입력하세요');
        setContentMessage('내용을 입력하세요');//span에 메시지로 표시할때
        contentRef.current.focus();
        
      }
      if(titleRef.current.value.length===0 || contentRef.current.value.length===0) return;
      console.log('여기서 부모에서 내린 게시글 등록함수 호출');    
      const title=titleRef.current.value;
      const content=contentRef.current.value;
      handleBoardCreate(title,content);
  }
  return <>
      <div className="jumbotron">
        <h1>
          게시판 <small>등록 페이지</small>
        </h1>
      </div>
      <form>
        <div className="form-group">
          <label>
            <kbd className="lead">제목</kbd>
          </label>
          <input
            type="text"
            className="form-control"
            placeholder="제목을 입력하세요"
            name="title"
            ref={titleRef}
            onChange={(e)=>{

              e.target.value.length===0?setTitleMessage('제목을 입력하세요'):setTitleMessage('')

            }}
          />
          <span ref={titleSpanRef} style={{color:'#ff0000'}}>{titleMessage}</span>
        </div>
        <div className="form-group">
          <label>
            <kbd className="lead">내용</kbd>
          </label>
          {/* JSX에서는 textarea의 컨텐츠를 value속성으로 설정 */}
          <textarea
            className="form-control"
            rows="5"
            name="content"
            placeholder="내용을 입력하세요"
            ref={contentRef}
            onChange={(e)=>{
              e.target.value.length===0?setContentMessage('내용을 입력하세요'):setContentMessage('')
              }}
          ></textarea>
          <span ref={contentSpanRef} style={{color:'#ff0000'}}>{contentMessage}</span>
        </div>
        <button className="btn btn-primary" onClick={handleArticle} >등록</button>
      </form>
    </>

};
export default Form;
