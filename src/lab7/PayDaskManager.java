package lab7;

public class PayDaskManager implements Runnable {
	
	private PayDesk payDesk;
	private volatile boolean running = true;
	
	public PayDaskManager(PayDesk payDesk, int N) {
		this.payDesk = payDesk;
		SnowboardersSimulation sBS = new SnowboardersSimulation(N, payDesk);
		sBS.manipulate();
	}
	
	public void terminate() {
		running = false;
	}

	@Override
	public void run() {
		while(running) {
			payDesk.collectMoney();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
//				e.printStackTrace();
				System.out.println("PayDesk " + this.toString());
				System.out.println("It has " + payDesk.getAvailableCards() + " available cards");
				System.out.println("And " + payDesk.getBalance() + " balance at the and of the day");
				break;
			}
		}		
	}

}
