package lab7;

import java.util.concurrent.ConcurrentHashMap;

import lab7.PayDeskExceptions.AlreadyExistsException;
import lab7.PayDeskExceptions.NotRegisteredYetException;

public class PayDesk {

	private final static ConcurrentHashMap<String, Snowboarder> swnowboarders = new ConcurrentHashMap<>();
	private volatile Integer balance = 0;
	private volatile Integer availableCards = 1000;
	private static volatile Integer vault = 0;

	public PayDesk() {

	}

	public void buyCard(String name) throws AlreadyExistsException {
		if (swnowboarders.containsKey(name)) {
			throw new AlreadyExistsException();
		}

		swnowboarders.put(name, new Snowboarder(name));
		
		reduceCards();
	}
	
	private synchronized void reduceCards() {
		while(availableCards == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		availableCards -= 1;
		
		while (balance > 20_000) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		
		balance += 51;		
	}
	
	private synchronized void increaseCards() {
		while(balance > 20_000) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		availableCards += 1;
		balance -= 1;
		notifyAll();
	}

	public void returnCard(String name) throws NotRegisteredYetException {
		if (!swnowboarders.containsKey(name)) {
			throw new NotRegisteredYetException();
		}

		swnowboarders.remove(name);
		
		increaseCards();
	}
	
	public synchronized int getBalance() {
		return balance;
	}
	
	public synchronized int getAvailableCards() {
		return availableCards;
	}
	
	public synchronized void collectMoney() {
		if(balance < 20_000) {
			return;
		}
		
		int deposit = 1000 - availableCards;
		vault += balance - deposit;
		balance = deposit;
		notifyAll();
	}
	
	public static synchronized int getVault() {
		return vault;
	}

}
