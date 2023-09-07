package com.kosmo.springapp.onememo.service.impl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosmo.springapp.onememo.service.LineCommentDTO;
import com.kosmo.springapp.onememo.service.ListPagingData;
import com.kosmo.springapp.onememo.service.OneMemoService;

@Service//서비스니까 서비스 어노테이션 붙여줌
public class LineCommentServiceImpl implements OneMemoService<LineCommentDTO>{
	//LineCommentDTO 로 임플리먼트하고 오버라이드로 쭉 아래 뽑음
	
	//매퍼 인터페이스 주입
	@Autowired
	private LineCommentMapper mapper;
	
	@Override
	public boolean isLogin(Map map) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ListPagingData<LineCommentDTO> selectList(Map map, HttpServletRequest req, int nowPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LineCommentDTO selectOne(Map map) {		
		return mapper.findByLno(map);
	}

	@Override
	public int insert(Map map) {
		//영향받은 행의 수가 아니라 새로 입력된 레코드의 키값(lno)를 반환 받자
		//마이바티스의 insert()는 무조건 영향받은 행의 수를 반환
		//매퍼파일의 <selectkey>요소 사용
		return mapper.save(map);
	}

	@Override
	public int update(LineCommentDTO record) {		
		return mapper.updateByLno(record);
	}

	@Override
	public int delete(LineCommentDTO record) {
		return mapper.deleteByLno(record);
	}

	public String getIdByLno(String lno) {		
		return mapper.findIdByLno(lno);
	}

}
