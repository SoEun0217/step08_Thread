package ex0820;

public class InterruptedExam {

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
			
				while(true) {
					if(Thread.interrupted()) {
						break;
					}
					System.out.println("Thread 재미 있다.....");
					//Thread.sleep(100);//0.1초
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
		//interrupt는 실행중일 때 인식할 수 없다. 중지상태일 때 interrupt를 보고 빠져나가는 것.
		th.interrupt();
		
		
		
		System.out.println("*********메인 끝입니다********");
	}

}
