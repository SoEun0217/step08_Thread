package ex0819;

public class ThreadExam {

	public static void main(String[] args) {
		System.out.println("���� �����ϱ�");
		NumberThread th1=new NumberThread("ù��°Thread");
		NumberThread th2=new NumberThread("�ι�°Thread");
		
		AlphaThread at=new AlphaThread();
		Thread th3=new Thread(at,"����°Thread");
		
		//th1.run();
		//th2.run();
	
		th1.start();
		th2.start();
		th3.start();
		/*try {
		th1.join();//th1 �����尡 ���������� ������ ����� ���� ���Ѵ�.
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		System.out.println("���� : "+th1.sum);
		System.out.println("���� ��");
		
	}

}
////////////////////////////////////////////////////
/**
 *1~100���� ����ϴ� ������ 
 * */
class NumberThread extends Thread{
	int sum;
	public NumberThread(String name) {
		super(name);
	}
	/**
	 * ������� ������ ��� ����
	 * */
	@Override
	public void run() {
		for(int i=1;i<=100;i++) {
			System.out.println(super.getName()+"===>"+i);
			sum+=i;
//			try {
//			Thread.sleep(100);//0.1�� ���
//			}catch(InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		System.out.println(super.getName()+" End....");
	}
	
}


/**
 * A~Z���� ����ϴ� ������
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
			Thread.yield();//�纸
		}
		System.out.println(th.getName()+" End....");
	}
	 	 
 }
 ////////////////////////////////////////////////