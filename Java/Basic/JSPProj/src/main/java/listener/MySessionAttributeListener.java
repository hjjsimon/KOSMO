package listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;

@WebListener
public class MySessionAttributeListener implements HttpSessionAttributeListener {

    public MySessionAttributeListener() {
        System.out.println("MySessionAttributeListener의 생성자");//프로젝트 run으로 서버 실행시 생성
    }

    public void attributeReplaced(HttpSessionBindingEvent se)  { 
    	System.out.println("세션의 속성이 변경됨:"+se.getSession().getId());
    	System.out.println(String.format("변경된 속성명:%s,속성값:%s", se.getName(), se.getValue()));
    }

    public void attributeRemoved(HttpSessionBindingEvent se)  { 
    	System.out.println("세션의 속성이 삭제됨:"+se.getSession().getId());
    	System.out.println(String.format("삭제된 속성명:%s,속성값:%s", se.getName(), se.getValue()));
    }

    public void attributeAdded(HttpSessionBindingEvent se)  {//http://localhost:8080/JSPProj/session06/SessionIndex.jsp 실행해봄
    	System.out.println("세션의 속성이 추가됨:"+se.getSession().getId());//세션의 속성이 추가됨:FFE43FDF57BBCEBF1EF23B52E1BFDF0F
    	System.out.println(String.format("추가된 속성명:%s,속성값:%s", se.getName(), se.getValue()));//추가된 속성명:sessionScope,속성값:세션 영역입니다
    }
	
}
