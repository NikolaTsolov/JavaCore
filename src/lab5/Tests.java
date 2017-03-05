package lab5;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Test;

import lab2.TODOList;
import lab2.Task;
import lab2.Task.Statuses;

public class Tests {

	@Test
	public void test() {
		Task task1 = new Task("asdf", "asdfh", Statuses.DONE, 5, LocalDate.of(1, 11, 1));
		TODOList todoList = new TODOList();
		Task[] tasks = new Task[]{task1};
		TODOList todoList2 = new TODOList(tasks);
		todoList.add(task1);
		Arrays.sort(todoList2.getTasks());
		Arrays.sort(todoList.getTasks());
		assertArrayEquals(todoList.getTasks(), new Task[] {task1});
//		System.out.println(Arrays.toString(todoList.getTasks()));
	}

}
