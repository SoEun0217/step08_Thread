package ex0819;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class GUIThreadExam extends JFrame {

	private JTextField text1=new JTextField(5);
	private JTextField text2=new JTextField(5);
	private JButton btn=new JButton("클릭");
	
	/**
	 * 화면 구성하기
	 * */
	public GUIThreadExam() {
		
		//레이아웃 변경
		super.setLayout(new FlowLayout());
		
		//Container위에 컴포넌트 추가
		Container con=super.getContentPane();
		con.add(text1);
		con.add(text2);
		con.add(btn);
		//창의 크기
		super.setSize(500, 400);
		//창의 위치
		super.setLocationRelativeTo(null);//정가운데 놓이기 setSize가 먼저 지정되야함
		
		//보여줘
		super.setVisible(true);
		//x클릭 종료
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//시계Thread  생성
		new TimeThread(this).start();//내가 곧 GUI이므로 this가 인수이다.
		
		//숫자넣기
		new DecimalThread().start();
		
		//버튼을 클릭하면 기능(이벤트처리)
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//기능
				new Thread() {
					public void run() {
						for(char c='A';c<='Z';c++) {
							text2.setText(c+"");
							try {
							Thread.sleep(100);
							}catch(InterruptedException e) {
								e.printStackTrace();
							}
						}
						
					};
				}.start();
			}
		});
	}
	public static void main(String[] args) {
		new GUIThreadExam();
	}//메인끝
	
	/**
	 * 1~1000까지 출력하는 스레드
	 * */
	
	class DecimalThread extends Thread{
		@Override
		public void run() {
			for(int i=1;i<1000;i++) {
				text1.setText(i+"");
				try {
				Thread.sleep(100);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}	
	}

}//클래스 끝


/////////////////////////////////////////////////////
/**
 * JFrame위에 시계를 동작시킬 스레드
 * */
class TimeThread extends Thread{
	GUIThreadExam gui;
	public TimeThread(GUIThreadExam gui) {
		this.gui=gui;
	}
	@Override
	public void run() {
		while(true) {
		//현재 날짜,시간
		Calendar now=Calendar.getInstance();
	
		int y=now.get(Calendar.YEAR);
		int m=now.get(Calendar.MONTH)+1;
		int d=now.get(Calendar.DATE);
		
		int h=now.get(Calendar.HOUR_OF_DAY);
		int min=now.get(Calendar.MINUTE);
		int s=now.get(Calendar.SECOND);
		
		String time=y+"년 "+m+"월 "+d+"일 "+h+"시 "+min+"분 "+s+"초 ";
		gui.setTitle(time);
		try {
		Thread.sleep(1000);//1000분의 1000이기때문에 1초이다.
		}catch(InterruptedException e) {
			e.printStackTrace();
			}
		}
		
	}
	
}
//////////////////////////////////////////////////











