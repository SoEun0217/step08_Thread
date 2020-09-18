package ex0820;

public class SyncronizedExaam {

	public static void main(String[] args) {
		System.out.println("**************���� �����մϴ�******************");
		Bank bank = new Bank();
		CustomerThread th1 = new CustomerThread(bank, "������", true);
		CustomerThread th2 = new CustomerThread(bank, "�Һ���", false);// ���� ���� bank�� �����Ѵ�.

		th1.start();
		th2.start();

		System.out.println("**************���� �����մϴ�******************");
	}

}

//////////////////////////////////////////
/**
 * ���� �����尡 ������ ��ü
 */
class Bank {
	int balance;

	/**
	 * �ܾ��� (�Ա� or ���) �����ϴ� �޼ҵ�
	 * 
	 * @param : String�� ������ or �Һ��� state�� true�̸� ����(�Ա�) false�̸� �Һ�(���)
	 * 
	 * 
	 *          : synchronized ���ȿ��� ����� �� �ִ� �޼ҵ� wait(); - ���� �����带 �������·� ����
	 *          notify(); - wait();�� ���� ���������� ������ �߿� �켱���� ���� �����带 ������ �����·� ����
	 *          notifyAll(); - wait()�� ���� ���������� ��罺���� ������ �����·� ����
	 *          
	 *          *�ݵ�� synchronized �� �ȿ��� wait() notify()����� �� �ִ�.
	 *          �ƴϸ� IllegalMonitorStateException �߻��Ѵ�.
	 */
	public synchronized void balanceChange(String name, boolean state) {// name�� �Ա����� ������� ����
		if (state) {
			if (balance == 0) {
				System.out.print(name + "===>�����ܾ� : " + balance + " , ");
				balance++;
				System.out.println(name + "===>���� �� �ܾ� : " + balance);
			} else {
				System.out.println(name + " ����� �Դϴ�......");
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
//			//synchronized (this) {//this�� ������ü�� ����
//				System.out.print(name+"===>�����ܾ� : "+balance+" , ");
//				balance++;
//				System.out.println(name+"===>���� �� �ܾ� : "+balance);
//			//}

		} else {
			if (balance > 0) {
				// synchronized (this) {
				System.out.print(name + "===>�����ܾ� : " + balance + " , ");
				balance--;
				System.out.println(name + "===>���� �� �ܾ� : " + balance);
				// }

			} else {
				System.out.println(name + "����� �Դϴ�....");
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		notifyAll();

	}// �޼ҵ� ��
}// Ŭ������
///////////////////////////////////////////////////////
//���� Bank�� Customer class�ȿ� �����ؼ� ����Ѵٸ� Thread�� �����Ҷ����� Bank�� ���� �������

class CustomerThread extends Thread {
	Bank bank;
	String name;
	boolean state;

	CustomerThread(Bank bank, String name, boolean state) {
		super(name);// �����忡�̸��� ��.
		this.bank = bank;
		this.name = name;
		this.state = state;
	}

	@Override
	public void run() {
		for (int i = 0; i < 50; i++) {
			bank.balanceChange(name, state);
		}
		System.out.println(super.getName() + "Thread ����....");
	}
}