package innerclass22;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InnerAnonymousEvent extends JFrame{//내부클래스 안쓰고 익명클래스로 해결
	
	JButton button1,button2,button3;
	//방법2]1번 중복코딩 많음, addActionListner에 ActionListner타입 넣어야하니까 애초에 변수로 만듦
	private ActionListener handler=new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==button1)
				JOptionPane.showMessageDialog(button1, "첫번째 버튼을 클릭했어요");
			else if(e.getSource()==button2)
				JOptionPane.showMessageDialog(button2, "두번째 버튼을 클릭했어요");
			else
				JOptionPane.showMessageDialog(button3, "세번째 버튼을 클릭했어요");
		}
	};
	
	public InnerAnonymousEvent(){
		setTitle("내부 익명 클래스로 이벤트 처리하기"); 
		setLayout(new FlowLayout()); 
		add(button1=new JButton("1ST버튼"));
		add(button2=new JButton("2ND버튼"));
		add(button3=new JButton("3RD버튼"));
		//익명클래스로 윈도우종료 이벤트처리]
		addWindowListener(new WindowAdapter() {//익명클래스 만들어짐, 여기서 원하는 것만 오버라이딩하면됨, 윈도우어뎁터는 윈도우리스너를 상속
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//ActionListner를 상속받은 익명클래스로 버튼의 이벤트처리] 
		//방법1]
/*		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(button1, "첫번째 버튼 클릭했어요");//이벤트 발생한게 버튼1, 이전에는 if로 따졌는데 지금은 그럴필요없음
			}
		});
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(button2, "두번째 버튼 클릭했어요");//이벤트 발생한게 버튼1, 이전에는 if로 따졌는데 지금은 그럴필요없음
			}
		});
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(button3, "세번째 버튼 클릭했어요");//이벤트 발생한게 버튼1, 이전에는 if로 따졌는데 지금은 그럴필요없음
			}
		});
*/		//방법2]1번 귀찮 이게 낫다!
		button1.addActionListener(handler);
		button2.addActionListener(handler);
		button3.addActionListener(handler);
		
		pack();
		setVisible(true);
	}
	
	
	public static void main(String[] args) {
		
		new InnerAnonymousEvent(); 
	}
}
