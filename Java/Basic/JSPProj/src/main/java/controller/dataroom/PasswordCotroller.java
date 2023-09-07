package controller.dataroom;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FileUtils;
import model.dataroom.DataRoomDAO;
import model.dataroom.DataRoomDTO;

@WebServlet("/DataRoom/Password.kosmo")
public class PasswordCotroller extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//post로 넘어오므로 doPost 오버라이딩
		//파라미터 받기]
		String no = req.getParameter("no");
		String mode = req.getParameter("mode");
		String orignalFileName = req.getParameter("orignalFileName");
		String password = req.getParameter("password");
		//System.out.println("모드:"+mode);//수정누르고 확인버튼 누르면 모드:UPDATE
		//모델 호출 및 결과값 받기]
		DataRoomDAO dao = new DataRoomDAO(getServletContext());
		boolean flag = dao.isCorrectPassword(no,password);//no랑 password넘겨줌, 비밀번호 맞으면 t, 아니면 f
		dao.close();
		//뷰 선택 후 포워딩]
		//[비밀번호가 틀린 경우는 이전 페이지로]
		if(!flag) {//자바스크립트로 해줌
			resp.setContentType("text/html; charset=UTF-8");//넣어야 한글 안깨짐
			PrintWriter out = resp.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 일치하지 않아요');");
			out.println("history.back();");
			out.println("</script>");
			return;
		}
		//[비밀번호가 일치하는 경우]
		if("UPDATE".equals(mode))//수정버튼 누른 경우, 수정폼으로 이동
			req.getRequestDispatcher("/DataRoom/Edit.kosmo").forward(req, resp);//JSP로 가는거x, 컨트롤러로 감
		else {//삭제버튼 누른 경우-삭제 처리후 메시지 뿌려주는 페이지로 이동
			dao = new DataRoomDAO(getServletContext());//dao다시 열어줘야함
			DataRoomDTO dto = new DataRoomDTO();
			dto.setNo(no);
			int deleteFlag = dao.delete(dto);//레코드삭제 성공은 1, 실패는 0, no넘겨야함
			dao.close();
			if(deleteFlag == 1) {//데이터 삭제후 서버에 업로드된 파일들 삭제
				FileUtils.deletes(new StringBuffer(orignalFileName), req.getServletContext().getRealPath("/upload"), ",");
				//첫번째인자 파일명, 두번째인자 물리적경로
			}
			req.setAttribute("SUCCFAIL", deleteFlag);
			//메시지용 페이지로 포워드
			req.getRequestDispatcher("/WEB-INF/dataroom14/Message.jsp").forward(req, resp);
			
		}
		
		
		
		
		
		
		
		
	}
	
}
