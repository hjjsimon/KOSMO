package controller.dataroom;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PagingUtil;
import model.dataroom.DataRoomDAO;
import model.dataroom.DataRoomDTO;


//1]사용자 요청을 받을 수 있도록 서블릿 클래스로 만들기(HttpServlet상속)즉 컨트롤러로 만들기
//서블릿 초기화 파라미터 이걸로 설정가능(GetController 참고)
//컨트롤러 요청 url은 디렉토리 구조와 무관 
@WebServlet(urlPatterns = "/DataRoom/List.kosmo",initParams = {@WebInitParam(name = "PAGE-SIZE",value = "2"),@WebInitParam(name = "BLOCK-PAGE",value = "2")})
public class ListController extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get인지 post인지 모르겠으면 service로 하면 됨
		//가]사용자 요청을 받는다	
		//나]요청을 분석한다.
		//다]모델에서 필요한 로직 호출해서 결과값이 있으면 받기 
		DataRoomDAO dao = new DataRoomDAO(getServletContext());
		//페이징 로직 호출
		Map map = new HashMap();
		PagingUtil.setMapForPaging(map, dao, req, this);//빈 맵을 전달, 설정함, 마지막껀 HttpServlet이니까 this 하면 됨
		int totalRecordCount=Integer.parseInt(map.get(PagingUtil.TOTAL_RECORD_COUNT).toString());
		int pageSize=Integer.parseInt(map.get(PagingUtil.PAGE_SIZE).toString());
		int blockPage=Integer.parseInt(map.get(PagingUtil.BLOCK_PAGE).toString());
		int nowPage=Integer.parseInt(map.get(PagingUtil.NOWPAGE).toString());
		String pagingString=PagingUtil.pagingBootStrapStyle(totalRecordCount, pageSize, blockPage, nowPage, req.getContextPath()+"/DataRoom/List.kosmo?");
		List<DataRoomDTO> records= dao.selectList(map);
		dao.close();
		//라]결과값이 있으면 리퀘스트 영역에 저장
		req.setAttribute("records", records);
		req.setAttribute("paging", pagingString);
		//마]결과값을 뿌려줄 뷰(JSP페이지) 선택후 포워딩 
		//뷰선택]
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/dataroom14/List.jsp");
		//포워딩]
		dispatcher.forward(req, resp);
		
	}///////////////////
}
