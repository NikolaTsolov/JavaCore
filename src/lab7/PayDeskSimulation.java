package lab7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PayDeskSimulation {
	private int payDesks;
	
	public PayDeskSimulation(int payDesks) {
		this.payDesks = payDesks;
	}
	
	public void manipulate() {
		ExecutorService es = Executors.newFixedThreadPool(payDesks);
		
		for (int i = 0; i < payDesks; i++) {
			es.execute(new PayDaskManager(new PayDesk(), 1100));
		}
		
		while(!es.isTerminated()) {
			try {
				Thread.sleep(3_000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			es.shutdownNow();
		}		
		
		System.out.println("There are " + PayDesk.getVault() + " money in the vault");
	}
	
	
}
