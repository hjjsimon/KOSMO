package com.kosmo.springapp.basic.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kosmo.springapp.onememo.service.OneMemoDTO;

@Mapper
public interface MyBatisMapper {
	
	
	String getTimeByXml();

	List<Map> findWithTitleLike(Map map);
	
	List<OneMemoDTO> findWithColumnsLike(Map map);
	
	List<OneMemoDTO> findWithColumnsLikeChoose(Map map);
	
	List<OneMemoDTO> findWithColumnsLikeWhere(Map map);
	
	List<OneMemoDTO> findWithColumnsLikeTrim1(Map map);
	
	int updateWithColumnsLikeTrim2(Map map);

	int updateWithColumnsLikeSet(Map map);
	
	
	List findAll(List<Integer> numbers);
	
	List findAll(Map<String, List> map);
	
	int deleteEmail(int[] emails);
}
