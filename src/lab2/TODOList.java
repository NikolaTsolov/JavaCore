package lab2;

import java.time.LocalDate;
import java.util.Arrays;

import lab2.Task.Statuses;

public class TODOList {
	
	private Task [] todoList;
	private int size;
	
	public TODOList(Task[] todoList) {
		size = 0;
		addTasks(todoList);
	}
	
	public TODOList() {
		this.todoList = new Task[0];
		size = 0;
	}
	
	public Task [] getTasks() {
		return Arrays.copyOf(todoList, todoList.length);
	}

	public void add(Task task) {
		todoList = Arrays.copyOf(todoList, todoList.length + 1);
		todoList[size++] = task;
	}
	
	public void addTasks(Task[] tasks) {
		if(size == 0) {
			this.todoList = new Task[tasks.length];
		} else {
			todoList = Arrays.copyOf(todoList, size + tasks.length);
		}
		
		for (int i = 0; i < tasks.length; i++) {
			todoList[size++] = tasks[i];
		}
	}

	public Task[] getTasksByPriority() {
		final Task[] tmp = Arrays.copyOf(todoList, todoList.length);
		Arrays.sort(tmp);
		return tmp;
	}
	
	public Task[] getTasksInProcess() {
		Task[] tmpTasks = new Task[todoList.length];
		int iterator = 0;
		
		for (Task task : todoList) {
			if(task.getStatus().equals(Statuses.IN_PROCESS)) {
				tmpTasks[iterator++] = task;
			}
		}		
		
		Task[] tasksInProcess = new Task[iterator];
		
		System.arraycopy(tmpTasks, 0, tasksInProcess, 0, iterator);
		
		Arrays.sort(tasksInProcess);
		
		return tasksInProcess;		
	}
	
	private boolean isUrgent(LocalDate now, LocalDate deadline) {
		LocalDate newDate = deadline.minusDays(3);

		if(now.isAfter(newDate) || now.equals(newDate)) {
			return true;
		}
		
		return false;
	}
	
	public Task[] getTasksForThreeDays() {
		Task[] tmpTasks = new Task[todoList.length];
		int iterator = 0;
		
		for (Task task : todoList) {
			boolean isUrgent = isUrgent(LocalDate.now(), task.getDeadline());
			boolean isInProcess = task.getStatus().equals(Statuses.IN_PROCESS);
			boolean isInitial = task.getStatus().equals(Statuses.INITIAL);
			
			if((isInProcess || isInitial) && isUrgent) {
				tmpTasks[iterator++] = task;
			}
		}
		
		Task[] tasksInProcess = new Task[iterator];
		
		System.arraycopy(tmpTasks, 0, tasksInProcess, 0, iterator);
		
		return tasksInProcess;		
	}
	
}
