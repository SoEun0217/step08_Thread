package ex0820;

public class InterruptExam {

	public static void main(String[] args) {
		System.out.println("******���� �����Դϴ�***********");
		
//		new Thread() {
//			public void run() {
//				
//			};
//		}.start();
//		
		Thread th=new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
				while(true) {
					System.out.println("Thread ��� �ִ�.....");
					Thread.sleep(100);//0.1��
					}
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		th.start();
		
		try {
		Thread.sleep(5000);
		}catch(InterruptedException e ) {
			e.printStackTrace();
		}
		//������ ������Ű��..
		//�����常 ���� ��Ų�ſ��� ���γ��� ��µ�.
		
		th.interrupt();
		
		
		
		System.out.println("*********���� ���Դϴ�********");
	}

}
