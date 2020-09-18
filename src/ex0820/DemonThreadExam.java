package ex0820;

public class DemonThreadExam {

	public static void main(String[] args) {
		System.out.println("**********메인 시작****************");

		Thread th = new Thread() {
			@Override
			public void run() {
				try {
					while (true) {
						System.out.println("DaemonThread임니다");
						Thread.sleep(200);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};
		th.setDaemon(true);// 데몬 스레드(주스레드가 종료되면 자동종료된다)
		th.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
