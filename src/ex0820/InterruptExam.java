package ex0820;

public class InterruptExam {

	public static void main(String[] args) {
		System.out.println("******메인 시작입니다***********");
		
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
					System.out.println("Thread 재미 있다.....");
					Thread.sleep(100);//0.1초
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
		//스레드 중지시키기..
		//스레드만 중지 시킨거여서 메인끝이 출력됨.
		
		th.interrupt();
		
		
		
		System.out.println("*********메인 끝입니다********");
	}

}
