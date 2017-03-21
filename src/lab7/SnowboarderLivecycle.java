package lab7;

import lab7.PayDeskExceptions.AlreadyExistsException;
import lab7.PayDeskExceptions.NotRegisteredYetException;

public class SnowboarderLivecycle implements Runnable {
	
	private PayDesk payDesk;
	private String name;
	
	public SnowboarderLivecycle(PayDesk payDesk, String name) {
		this.payDesk = payDesk;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			payDesk.buyCard(name);
		} catch (AlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			payDesk.returnCard(name);
		} catch (NotRegisteredYetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
