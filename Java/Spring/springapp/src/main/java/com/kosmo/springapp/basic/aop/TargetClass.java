package com.kosmo.springapp.basic.aop;

import org.springframework.stereotype.Component;

//공통관점(공통로직-공통기능 코드(Advice)을 주입받는(위빙) 대상 클래스

@Component
public class TargetClass {

	//핵심 로직]- 핵심로직 전후에 공통관점(Around Advice) 주입-위빙한다.
	//Around Advice: 공통관점(=공통로직, cross-cutting concern), 귀찮은것들 다른 CrossCuttingConcern에 넣고 가져오기만함
	public long getTotal() {
		long total=0;
		for(long i=1;i<=1000000000;i++) total+=i;
		return total;
	}
	
}
