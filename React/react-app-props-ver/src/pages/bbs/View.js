import { Link, useNavigate, useParams } from "react-router-dom";
import useFetch from "../../hooks/useFetch";
import React from "react";
const View = ({hanldeBoardDelete})=>{
	//1.URL파라미터로 넘어오는 게시글의 키(ID)값 받기
	const params = useParams();
	//2.id에 해당하는 글 읽어 오기
	const board=useFetch(`http://localhost:3002/bbs/${params.id}`)[0];//데이타만 가져오자(세터는 필요없다)
	console.log('board.content:',board.content)

	//수정폼으로 이동하기위한 훅함수 객체 생성
	const navigate=useNavigate();
	return <>
    <div className="jumbotron">
			<h1>게시판 <small>상세보기 페이지</small></h1>
		</div>
		<table className="table table-bordered">
			<tbody className="table-sm">
				<tr>
					<th className="w-25 bg-dark text-white text-center">번호</th>
					<td>{board.id}</td>
				</tr>
				<tr>
					<th className="w-25 bg-dark text-white text-center">글쓴이</th>
					<td>{board.username}</td>
				</tr>
				<tr>
					<th className="w-25 bg-dark text-white text-center">작성일</th>
					<td>{board.postDate}</td>
				</tr>
				<tr>
					<th className="w-25 bg-dark text-white text-center">조회수</th>
					<td>{board.visitCount}</td>
				</tr>
				<tr>
					<th className="w-25 bg-dark text-white text-center">제목</th>
					<td>{board.title}</td>
				</tr>
				<tr>
					<th className="bg-dark text-white text-center" colSpan="2">내 용</th>
				</tr>
				<tr>
					{/*https://reactjs.org/docs/dom-elements.html
					   XSS공격을 막기위해 코드로 '\n'을 '<br/>'문자열로 변경시 태그가 아닌 문자열로 렌더링
						 즉 <br/>이 "<br/>" 형식으로.. 렌더링
						 예:board.content.replace('\n','<br/>') 즉 <br/>태그로 렌더링이 안되고
						 '<br/>'문자열로 렌더링이 되어 브라우저에 그대로 태그가 보인다
						 문자열이 아닌 <br>태그를 반환하면 된다						 
					*/}
					<td colSpan="2">{board.content && board.content.split('\n').map((text,index)=><React.Fragment key={index}>{text}<br/></React.Fragment>)}</td>
				</tr>
			</tbody>
		</table>
		<div className="text-center">
      <button className="btn btn-success" onClick={()=>{
				{/* 상세보기의 글을 state로 Form컴포넌트로 전달 */}
				navigate('/boards/form',{state:board})
			}}>수정</button>
      <button className="btn btn-success mx-2" onClick={()=>{
				//JSX에서 confirm()을 사용시에는 반드시 앞에 window객체를 붙인다
				if(window.confirm('정말로 삭제 하시겠습니까?')){
					hanldeBoardDelete(board.id);
				}
			}} >삭제</button>
			
			<Link to="/boards" className="btn btn-success">목록</Link>
		</div>
  </>
};
export default View;