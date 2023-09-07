package com.kosmo.springapp.basic.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.kosmo.springapp.onememo.service.OneMemoDTO;


@Service
public class MyBatisService {
	
	private MyBatisMapper mapper;

	@Autowired
	public MyBatisService(MyBatisMapper mapper) {
		this.mapper = mapper;
	}
	
	public String getTimeByXml() {
		return mapper.getTimeByXml();
	}

	public List<Map> if1(Map map) {
		return mapper.findWithTitleLike(map);
	}

	public List<OneMemoDTO> if2(Map map) {
		return mapper.findWithColumnsLike(map);
	}

	public List<OneMemoDTO> choose(Map map) {
		return mapper.findWithColumnsLikeChoose(map);
	}

	public List<OneMemoDTO> where(Map map) {
		return mapper.findWithColumnsLikeWhere(map);
	}

	public List<OneMemoDTO> trim1(Map map) {
		return mapper.findWithColumnsLikeTrim1(map);
	}

	public int trim2(Map map) {
		return mapper.updateWithColumnsLikeTrim2(map);
	}

	public int set(Map map) {
		return mapper.updateWithColumnsLikeSet(map);
	}

	public List foreach(List<Integer> numbers) {
		return mapper.findAll(numbers);
	}
	
	public List foreach(Map<String, List> map) {
		return mapper.findAll(map);
	}
	
	@Autowired
	private TransactionTemplate transactionTemplate;
	
	public int foreachExam(int[] emails) {
		int deleteCount = transactionTemplate.execute(status->{
			int affected = 0;
			
			try {
				affected = mapper.deleteEmail(emails);
			}
			catch (Exception e) {
				e.printStackTrace();
				affected = -1;
			}
			return affected;
		});
		return deleteCount;
	}
	
	
	
}
