package controller.dataroom;

import java.io.IOException;
import java.util.Collection;

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

@WebServlet("/DataRoom/Edit.kosmo")
@MultipartConfig(maxFileSize = 1024 * 500,maxRequestSize = 1024*500*5)
public class EditController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//PasswordController에서 post로 받은 요청을 그대로 여기로 보내니까 이것도 doPost
		/*
		  	모두(수정폼으로 이동요청과 수정처리요청) POST요청이므로
		  	수정폼으로 이동 및 수정처리는
		  	전달된 파라미터로 구분하거나 이전 URL로 구분하여 분기처
		 */
		//System.out.println("mode:"+req.getParameter("mode")+",이전 URL:"+req.getHeader("referer"));
		//mode:UPDATE, 이전 URL: View.kosmo, 파라미터 그대로 전송, 등록버튼 누르면 mode:null, 이전 URL: Password.kosmo
		//mode가 UPDATE인지 null인지 보고 판단하면 됨
		//이전 URL 구하는건 getHeader("referer")
		String mode = req.getParameter("mode");
		if(mode != null) {//수정 폼으로 이동
			//파라미터 받기]
			String no = req.getParameter("no");
			//모델 호출 및 결과값 받기]
			DataRoomDAO dao = new DataRoomDAO(getServletContext());
			DataRoomDTO record = dao.selectOne(no);
			dao.close();
			//리퀘스트 영역에 저장
			req.setAttribute("record", record);
			//포워드
			req.getRequestDispatcher("/WEB-INF/dataroom14/Edit.jsp").forward(req, resp);
			return;
		}
		//수정처리
		//DB수정 성공시에는 1, 실패시 0, 파일용량 초과시에는 -1 저장
		//DB입력 성공시에는 1,실패시 0, 파일용량 초과시에는 -1 저장
		//WriteController에서 복붙
		int updateFlag;
		try {
			//파일을 저장할 서버의 물리적 경로
			String saveDirectory=req.getServletContext().getRealPath("/upload");
			//기타 파라미터 받기
			String name = req.getParameter("name");
			String title = req.getParameter("title");
			String password = req.getParameter("password");
			String content = req.getParameter("content");
			String no = req.getParameter("no");
			String originalFileName = req.getParameter("originalFileName");
			//업로드된 파일 정보를 얻기 위한 Part객체 생성
			Collection<Part> parts=req.getParts();//파일용량 초과시 에러
			
			//파일 업로드 로직 호출
			StringBuffer fileNames= FileUtils.upload(parts, saveDirectory);
			boolean isFileChange = fileNames==null?false:true;//null이면 교체안한것, false줌
		
			if(fileNames ==null) {//기존 파일을 제외하고 수정시, 즉 파일을 미첨부한 경우 즉 기존 파일을 StringBuffer로 교체   
				fileNames = new StringBuffer(originalFileName);
				
			}
			//수정 처리로직 호출(데이타베이스 CRUD작업과 관련된 모델 호출)
			DataRoomDAO dao = new DataRoomDAO(getServletContext());
			DataRoomDTO dto = new DataRoomDTO();
			dto.setContent(content);
			dto.setName(name);
			dto.setPassword(password);
			dto.setTitle(title);
			dto.setAttachFile(fileNames.toString());//파일명이 여러개면 ,로 구분되어 저장
			dto.setNo(no);
			updateFlag=dao.update(dto);//입력받은 행의 수
			if(updateFlag == 0 && isFileChange) {//수정 실패하고 파일 교체한 경우 교체한 파일 삭제
				//파일 삭제 로직 호출
				FileUtils.deletes(fileNames, saveDirectory, ",");
			}
			if(updateFlag == 1 && isFileChange) {//수정 성공하고 파일 교체한 경우 기존에 업로드된 파일 삭제
				//파일 삭제 로직 호출
				FileUtils.deletes(new StringBuffer(originalFileName), saveDirectory, ",");
			}
			
		}
		catch(Exception e) {//파일용량 초과시 catch들어옴
			updateFlag =-1;
		}
		
		//리퀘스트 영역에 결과값 혹은 필요한 값 저장
		//입력시 성공여부
		req.setAttribute("SUCCFAIL", updateFlag);
		//컨트롤러 구분용-입력:INS,수정:EDT,삭제:DEL
		req.setAttribute("WHERE", "EDT");
		//메시지용 페이지로 포워드
		req.getRequestDispatcher("/WEB-INF/dataroom14/Message.jsp").forward(req, resp);//JSP경로
		
		
	}//////
	
}
