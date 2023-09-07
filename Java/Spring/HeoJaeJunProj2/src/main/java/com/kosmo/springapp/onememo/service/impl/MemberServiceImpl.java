package com.kosmo.springapp.onememo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosmo.springapp.onememo.service.MemberDTO;
import com.kosmo.springapp.onememo.service.MemberService;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public void joinMember(MemberDTO member) {
		mapper.joinMember(member);
		
	}
	
	@Override
	public void updateFinishMember(MemberDTO member) {
		mapper.updateFinishMember(member);
		
	}
}
