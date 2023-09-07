package com.kosmo.springapp.onememo.service;

public interface MemberService {
	
	//회원가입처리
	void joinMember(MemberDTO member);
	//회원정보 수정처리
	void updateFinishMember(MemberDTO member);
}
