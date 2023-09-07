package controller.dataroom;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.FileUtils;
import model.dataroom.DataRoomDAO;
import model.dataroom.DataRoomDTO;

//하나의 컨트롤러로 두 가지 기능 처리
//1)즉 GET방식일때는 입력폼으로 이동
//2)POST방식일때는 입력처리
//1]HttpServlet상속-컨트롤러가 됨 즉 서블릿이 됨
@WebServlet("/DataRoom/Write.kosmo")
@MultipartConfig(maxFileSize = 1024 * 500,maxRequestSize = 1024*500*5)
public class WriteController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//2]요청분석- 입력폼 요청
		//3]모델호출 및 결과값 받기
		//4]결과값이 있으면 ,리퀘스트 영역에 저장
		//5]뷰 선택후 포워딩
		req.getRequestDispatcher("/WEB-INF/dataroom14/Write.jsp").forward(req, resp);
	}//////
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//DB입력 성공시에는 1,실패시 0, 파일용량 초과시에는 -1 저장
		//용량초과시 업로드 안되는데 DB에 저장될 수 있음, 그래서 업로드 성공 후 DB에 저장되도록 함
		int insertFlag;
		try {//FileUploadController복붙
			//파일을 저장할 서버의 물리적 경로
			String saveDirectory=req.getServletContext().getRealPath("/upload");
			//기타 파라미터 받기
			String name = req.getParameter("name");
			String title = req.getParameter("title");
			String password = req.getParameter("password");
			String content = req.getParameter("content");
			//업로드된 파일 정보를 얻기 위한 Part객체 생성
			Collection<Part> parts=req.getParts();//파일용량 초과시 에러
			//파일 업로드 로직 호출
			StringBuffer fileNames= FileUtils.upload(parts, saveDirectory);
			if(fileNames ==null) {//파일을 미 첨부한 경우(클라이언트 및 서버단에서 유효성 미 체크한 경우)
				req.setAttribute("ERROR","파일을 첨부하세요");
				req.getRequestDispatcher("/WEB-INF/dataroom14/Write.jsp").forward(req, resp);
				return;
			}
			//입력 처리로직 호출(데이타베이스 CRUD작업과 관련된 모델 호출)
			DataRoomDAO dao = new DataRoomDAO(getServletContext());
			DataRoomDTO dto = new DataRoomDTO();
			dto.setContent(content);
			dto.setName(name);
			dto.setPassword(password);
			dto.setTitle(title);
			dto.setAttachFile(fileNames.toString());//파일명이 여러개면 ,로 구분되어 저장
			insertFlag=dao.insert(dto);//입력받은 행의 수
			if(insertFlag==0) {//입력 실패시 업로드된 파일 삭제
				//파일 삭제 로직 호출
				FileUtils.deletes(fileNames, saveDirectory, ",");
			}
			
		}
		catch(Exception e) {//파일용량 초과시 catch들어옴
			insertFlag =-1;
		}
		//[이동방법1]입력 성공시에는 목록으로, 실패시나 파일용량 초과시에는 입력 폼으로
		/*
		if(insertFlag ==0 || insertFlag ==-1) {//실패시나 파일용량 초과시
			req.setAttribute("ERROR",insertFlag==0 ?"데이타 입력 오류":"최대 파일 용량 초과");
			req.getRequestDispatcher("/WEB-INF/dataroom14/Write.jsp").forward(req, resp);
		}
		else//성공시 바로 목록으로 이동:반드시 List.jsp가 아닌 List.kosmo로 이동해서 ListController에서 request로 저장 후 List.jsp로 이동시킴	
			req.getRequestDispatcher("/DataRoom/List.kosmo").forward(req, resp);
		*/
		
		//[이동방법2]-메시지 뿌려주는 JSP페이지(수정 및 삭제에서도 사용)로 이동후 목록으로 이동
		//리퀘스트 영역에 결과값 혹은 필요한 값 저장
		//입력시 성공여부
		req.setAttribute("SUCCFAIL", insertFlag);
		//컨트롤러 구분용-입력:INS,수정:EDT,삭제:DEL
		req.setAttribute("WHERE", "INS");
		//메시지용 페이지로 포워드
		req.getRequestDispatcher("/WEB-INF/dataroom14/Message.jsp").forward(req, resp);//JSP경로
		
		
		
	}/////
}////////////////////////
