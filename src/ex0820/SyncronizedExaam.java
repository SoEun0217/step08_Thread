package ex0820;

public class SyncronizedExaam {

	public static void main(String[] args) {
		System.out.println("**************메인 시작합니다******************");
		Bank bank = new Bank();
		CustomerThread th1 = new CustomerThread(bank, "생산자", true);
		CustomerThread th2 = new CustomerThread(bank, "소비자", false);// 둘이 같은 bank를 공유한다.

		th1.start();
		th2.start();

		System.out.println("**************메인 종료합니다******************");
	}

}

//////////////////////////////////////////
/**
 * 여러 스레드가 공유할 객체
 */
class Bank {
	int balance;

	/**
	 * 잔액을 (입금 or 출금) 변경하는 메소드
	 * 
	 * @param : String은 생산자 or 소비자 state는 true이면 생산(입금) false이면 소비(출금)
	 * 
	 * 
	 *          : synchronized 블럭안에서 사용할 수 있는 메소드 wait(); - 현재 스레드를 중지상태로 만듬
	 *          notify(); - wait();에 의해 중지상태인 스레드 중에 우선순위 높은 스레드를 깨워서 대기상태로 만듬
	 *          notifyAll(); - wait()에 의해 중지상태인 모든스레드 깨워서 대기상태로 만듬
	 *          
	 *          *반드시 synchronized 블럭 안에서 wait() notify()사용할 수 있다.
	 *          아니면 IllegalMonitorStateException 발생한다.
	 */
	public synchronized void balanceChange(String name, boolean state) {// name은 입금인지 출금인지 구분
		if (state) {
			if (balance == 0) {
				System.out.print(name + "===>현재잔액 : " + balance + " , ");
				balance++;
				System.out.println(name + "===>증가 후 잔액 : " + balance);
			} else {
				System.out.println(name + " 대기중 입니다......");
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
//			//synchronized (this) {//this는 공유객체를 뜻함
//				System.out.print(name+"===>현재잔액 : "+balance+" , ");
//				balance++;
//				System.out.println(name+"===>증가 후 잔액 : "+balance);
//			//}

		} else {
			if (balance > 0) {
				// synchronized (this) {
				System.out.print(name + "===>현재잔액 : " + balance + " , ");
				balance--;
				System.out.println(name + "===>감소 후 잔액 : " + balance);
				// }

			} else {
				System.out.println(name + "대기중 입니다....");
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		notifyAll();

	}// 메소드 끝
}// 클래스끝
///////////////////////////////////////////////////////
//만약 Bank를 Customer class안에 생성해서 사용한다면 Thread를 생성할때마다 Bank가 따로 만들어짐

class CustomerThread extends Thread {
	Bank bank;
	String name;
	boolean state;

	CustomerThread(Bank bank, String name, boolean state) {
		super(name);// 스레드에이름을 줌.
		this.bank = bank;
		this.name = name;
		this.state = state;
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			bank.balanceChange(name, state);
		}
		System.out.println(super.getName() + "Thread 종료....");
	}
}