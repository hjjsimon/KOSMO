package controller.dataroom;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dataroom.DataRoomDAO;
import model.dataroom.DataRoomDTO;

@WebServlet("/DataRoom/View.kosmo")
public class ViewController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//3]요청분석(List에서 a태그 클릭, Message에서 여기로 no들고 넘어옴)
		String no = req.getParameter("no");
		//4]모델 호출 및 결과값 받기
		DataRoomDAO dao = new DataRoomDAO(getServletContext());
		DataRoomDTO dto = dao.selectOne(no);
		dao.close();//데이터풀 반납
		//내용 줄바꿈 처리(리퀘스트영역 저장전에 해주는게 좋음)
		dto.setContent(dto.getContent().replace("\r\n", "<br/>"));
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("record", dto);//레코드라는 이름으로 저장
		//6]뷰 선택후 포워딩
		req.getRequestDispatcher("/WEB-INF/dataroom14/View.jsp").forward(req, resp);
		
		
	}

}
