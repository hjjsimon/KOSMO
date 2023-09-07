package innerclass22;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InnerMemberEvent extends JFrame{//이건 몰라도 됨, 내부클래스를 이용해 이벤트처리만 집중
	
	JButton button1,button2,button3;
	
	//내부클래스 인스턴스화]
	EventHandler handler=new EventHandler();
	
	public InnerMemberEvent(){//생성자 먼저 만듦
		setTitle("내부 멤버 클래스로 이벤트 처리하기"); //JFrame 상속받아 쓰는 메소드가 setTitle
		setLayout(new FlowLayout()); //new FlowLayout()이건 awt 패키지에 있는 클래스
		add(button1=new JButton("1ST버튼"));
		add(button2=new JButton("2ND버튼"));
		add(button3=new JButton("3RD버튼"));
		
		addWindowListener(handler);//윈도우창에 리스너 부착
		button1.addActionListener(handler);//버튼에 액션리스너 부착
		button2.addActionListener(handler);
		button3.addActionListener(handler);
		
		pack();
		setVisible(true);
	}
	//[내부 멤버 클래스]->외부클래스 모든 멤버 접근가능
	class EventHandler extends WindowAdapter implements ActionListener{//몰라도돼
		//윈도우의 시스템 이벤트 처리
		@Override
		public void windowClosing(WindowEvent e) {//우클릭 소스 윈도우클로징 오버라이딩, 프로그램 종료코드 넣음
			System.exit(0);//이벤트 처리위한 내부멤버 클래스 만듦, 이제 x누르면 프로그램 종료됨
		}
		//버튼의 클릭 이벤트 처리
		@Override
		public void actionPerformed(ActionEvent e) {//빨간줄 오버라이드, 버튼 클릭시(액션이벤트시) 그 정보가 e에 들어감,
			if(e.getSource()==button1)//이벤트가 발생한 객체의 주소를 가져옴, 이벤트 발생한 대상이 누구인지 물음
				JOptionPane.showMessageDialog(button1, "첫번째 버튼을 클릭했어요");//내부에 버튼1없음 외부에 있지만 자기것처럼 가져다씀
			else if(e.getSource()==button2)
				JOptionPane.showMessageDialog(button2, "두번째 버튼을 클릭했어요");
			else
				JOptionPane.showMessageDialog(button3, "세번째 버튼을 클릭했어요");
		}
	}
	
	public static void main(String[] args) {
		
		new InnerMemberEvent();//생성자 변수에 안담음, 변수 쓸 일이 없어서래->오 미친 윈도우창에 버튼이 나옴, x눌러서 윈도우창 꺼도 빨간불, 완전종료x 메모리만 살짝 운영체제에 맡긴거 
			
		
		
		
		
	}

}
