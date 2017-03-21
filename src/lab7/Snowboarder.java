package lab7;

import java.time.LocalDateTime;

public class Snowboarder {
	
	private String name;
	private LocalDateTime dateTime;
	
	public Snowboarder(String name) {
		this.name = name;
		dateTime = LocalDateTime.now();
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}

}
