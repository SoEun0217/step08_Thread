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
	private JButton btn=new JButton("Ŭ��");
	
	/**
	 * ȭ�� �����ϱ�
	 * */
	public GUIThreadExam() {
		
		//���̾ƿ� ����
		super.setLayout(new FlowLayout());
		
		//Container���� ������Ʈ �߰�
		Container con=super.getContentPane();
		con.add(text1);
		con.add(text2);
		con.add(btn);
		//â�� ũ��
		super.setSize(500, 400);
		//â�� ��ġ
		super.setLocationRelativeTo(null);//����� ���̱� setSize�� ���� �����Ǿ���
		
		//������
		super.setVisible(true);
		//xŬ�� ����
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�ð�Thread  ����
		new TimeThread(this).start();//���� �� GUI�̹Ƿ� this�� �μ��̴�.
		
		//���ڳֱ�
		new DecimalThread().start();
		
		//��ư�� Ŭ���ϸ� ���(�̺�Ʈó��)
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//���
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
	}//���γ�
	
	/**
	 * 1~1000���� ����ϴ� ������
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

}//Ŭ���� ��


/////////////////////////////////////////////////////
/**
 * JFrame���� �ð踦 ���۽�ų ������
 * */
class TimeThread extends Thread{
	GUIThreadExam gui;
	public TimeThread(GUIThreadExam gui) {
		this.gui=gui;
	}
	@Override
	public void run() {
		while(true) {
		//���� ��¥,�ð�
		Calendar now=Calendar.getInstance();
	
		int y=now.get(Calendar.YEAR);
		int m=now.get(Calendar.MONTH)+1;
		int d=now.get(Calendar.DATE);
		
		int h=now.get(Calendar.HOUR_OF_DAY);
		int min=now.get(Calendar.MINUTE);
		int s=now.get(Calendar.SECOND);
		
		String time=y+"�� "+m+"�� "+d+"�� "+h+"�� "+min+"�� "+s+"�� ";
		gui.setTitle(time);
		try {
		Thread.sleep(1000);//1000���� 1000�̱⶧���� 1���̴�.
		}catch(InterruptedException e) {
			e.printStackTrace();
			}
		}
		
	}
	
}
//////////////////////////////////////////////////











