package com.kosmo.springapp.onememo.service.impl;

import org.apache.ibatis.annotations.Mapper;

import com.kosmo.springapp.onememo.service.MemberDTO;

@Mapper
public interface MemberMapper {

	void joinMember(MemberDTO member);

	void updateFinishMember(MemberDTO member);
}
