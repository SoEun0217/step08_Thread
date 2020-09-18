package ex0819;

public class ThreadExam {

	public static void main(String[] args) {
		System.out.println("메인 시작하기");
		NumberThread th1=new NumberThread("첫번째Thread");
		NumberThread th2=new NumberThread("두번째Thread");
		
		AlphaThread at=new AlphaThread();
		Thread th3=new Thread(at,"세번째Thread");
		
		//th1.run();
		//th2.run();
	
		th1.start();
		th2.start();
		th3.start();
		/*try {
		th1.join();//th1 스레드가 끝날때까지 이하의 기능을 실행 안한다.
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		System.out.println("총합 : "+th1.sum);
		System.out.println("메인 끝");
		
	}

}
////////////////////////////////////////////////////
/**
 *1~100까지 출력하는 스레드 
 * */
class NumberThread extends Thread{
	int sum;
	public NumberThread(String name) {
		super(name);
	}
	/**
	 * 스레드로 동작할 기능 구현
	 * */
	@Override
	public void run() {
		for(int i=1;i<=100;i++) {
			System.out.println(super.getName()+"===>"+i);
			sum+=i;
//			try {
//			Thread.sleep(100);//0.1초 대기
//			}catch(InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		System.out.println(super.getName()+" End....");
	}
	
}


/**
 * A~Z까지 출력하는 스레드
 * */
 class AlphaThread implements Runnable{

	@Override
	public void run() {
		Thread th=Thread.currentThread();
		for(char c='A';c<='Z';c++) {
			System.out.println(th.getName()+"===>"+c);
//			try {
//			Thread.sleep(100);
//			}catch(InterruptedException e) {
//				e.printStackTrace();
//			}
			Thread.yield();//양보
		}
		System.out.println(th.getName()+" End....");
	}
	 	 
 }
 ////////////////////////////////////////////////