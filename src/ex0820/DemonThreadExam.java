package ex0820;

public class DemonThreadExam {

	public static void main(String[] args) {
		System.out.println("**********���� ����****************");

		Thread th = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println("DaemonThread�Ӵϴ�");
						Thread.sleep(200);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};
		th.setDaemon(true);// ���� ������(�ֽ����尡 ����Ǹ� �ڵ�����ȴ�)
		th.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
