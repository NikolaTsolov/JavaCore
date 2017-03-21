package lab7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SnowboardersSimulation {
	
	private int N;
	private PayDesk payDesk;
	
	public SnowboardersSimulation(int N, PayDesk payDesk) {
		this.N = N;
		this.payDesk = payDesk;
	}
	
	public void manipulate() {
		ExecutorService es = Executors.newFixedThreadPool(N);
		for (int i = 0; i < N; i++) {
			es.execute(new SnowboarderLivecycle(payDesk, "Snoboarder" + i + payDesk.hashCode()));
		}		
		es.shutdown();		
	}
	
}
