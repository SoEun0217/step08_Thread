package ex0820;

public class InterruptedExam {

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
			
				while(true) {
					if(Thread.interrupted()) {
						break;
					}
					System.out.println("Thread ��� �ִ�.....");
					//Thread.sleep(100);//0.1��
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
		//interrupt�� �������� �� �ν��� �� ����. ���������� �� interrupt�� ���� ���������� ��.
		th.interrupt();
		
		
		
		System.out.println("*********���� ���Դϴ�********");
	}

}
