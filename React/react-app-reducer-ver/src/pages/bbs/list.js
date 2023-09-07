import Pagination from "rc-pagination";
import { useContext } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { BoardsContext } from "../../context/bbs_context";

const List =()=>{

    const {boardsInfo,dispatch}=useContext(BoardsContext);
    const {boards,nowPage,pageSize,totalSize} = boardsInfo;
    console.log('(List.js)의 boards:',boards);
   
    //글 등록 버튼 및 제목 클릭시 페이지 이동을 위한 useNavigate훅 함수객체 생성
    const navigate = useNavigate();
   
    return <>
        <div className="jumbotron">
			<h1>게시판 <small>목록 페이지</small></h1>
		</div>
		<div className="text-right mb-2">
			<button className="btn btn-danger" onClick={()=>{              
              navigate('/boards/form');  
            }}>글 등록</button>
		</div>
		<table className="table table-dark table-hover text-center">
			<thead>
				<tr>
					<th className="col-1">번호</th>
					<th>제목</th>
					<th className="col-2">글쓴이</th>
					<th className="col-2">작성일</th>
					<th className="col-1">조회수</th>
				</tr>
			</thead>
			<tbody className="table-sm down-file-body">
          
                {boards.length ==0?
                    (<tr>
                        <td colSpan="5">등록된 글이 없습니다.</td>
                    </tr>)
                :
                boards.map(board=>(
                    <tr key={board.id} >
                        <td>{board.id}</td>
                        <td className="text-left" onClick={()=>navigate(`/boards/view/${board.id}`,{state:board})}>{board.title}</td>
                        <td>{board.username}</td>
                        <td>{board.postDate}</td>
                        <td>{board.visitCount}</td>
                    </tr>

                ))
                }
			</tbody>
		</table>
        {/* 페이징 표시:total={totalSize} current={nowPage} pageSize={pageSize}*/}
		{/* 페이징 표시는 되나 페이지 번호 클릭시 이벤트 처리가 안됨
		    가운데 정렬:className="d-flex justify-content-center" 추가(디폴트가 왼쪽에 페이징 표시)
				이벤트 처리:onChange={(nowPage)=>setNowPage(nowPage)}
				단,Boards에서 글 가져오는 훅 함수를 nowPage 스테이트가 
				변할때 마다로 바꾼다 
				즉 의존성 배열 []에서 [nowPage]로 변경한다 
		*/}
        <Pagination total={totalSize} current={nowPage} pageSize={pageSize} className="d-flex justify-content-center" onChange={(clickPage)=>dispatch({type:'nowPage',nowPage:clickPage})}/>

    </>
};

export default List;