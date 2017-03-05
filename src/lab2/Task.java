package lab2;

import java.time.LocalDate;

public class Task implements Comparable<Task> {

	private String title;
	private String description;

	public enum Statuses {
		INITIAL, IN_PROCESS, DONE
	};
	
	private Statuses status;
	private int priority;
	private LocalDate deadline;
	
	
	public Task(String title, String description, Statuses status, int priority, LocalDate deadline) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.deadline = deadline;
	}
	
	@Override
	public String toString() {
		StringBuilder toString = new StringBuilder();
		toString.append(String.format("%s : %s. ", title, description));
		toString.append(status);
		toString.append(String.format(" status and priority -> %d. Date created: ", priority));
		toString.append(deadline.toString());
		return toString.toString();
	}

	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Statuses getStatus() {
		return status;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public LocalDate getDeadline() {
		return deadline;
	}

	@Override
	public int compareTo(Task t) {
		if(t.getPriority() < this.priority) {
			return -1;
		} else if (t.getPriority() == this.priority) {
			return 0;
		} else {
			return 1;
		}
	}

}
