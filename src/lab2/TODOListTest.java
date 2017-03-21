package lab2;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lab2.Task.Statuses;

public class TODOListTest {
	
	Task task1;
	Task task2;
	Task task3;
	Task[] tasks;

	@Before
	public void setUp() throws Exception {
		task1 = new Task("asdf", "asdfh", Statuses.DONE, 5, LocalDate.of(2016, 11, 1));
		task2 = new Task("fdsa", "gfdsa", Statuses.IN_PROCESS,4, LocalDate.now().plusDays(2));
		task3 = new Task("qwer", "qwert", Statuses.INITIAL, 3, LocalDate.now().plusDays(3));
		tasks = new Task[]{task3, task2, task1};
	}
	
    @After
    public void tearDown() {
        task1 = null;
        task2 = null;
        task3 = null;
        tasks = null;
    }

	@Test
	public void testTODOListWithArray() {
		TODOList todoList = new TODOList(tasks);
		assertArrayEquals(todoList.getTasks(), tasks);
	}
	
	
	
	@Test
	public void testTODOListAndAdd() {
		TODOList todoList = new TODOList();
		todoList.add(task1);
		assertArrayEquals(new Task[]{task1}, todoList.getTasks());
	}
	
	@Test
	public void testAddTasks() {
		TODOList todoList = new TODOList();
		todoList.add(task1);
		todoList.addTasks(tasks);
		assertArrayEquals(new Task[]{task1, task3, task2, task1}, todoList.getTasks());
	}
	
	@Test
	public void testNormalConstructorAndAddAddTasks() {
		TODOList todoList = new TODOList();
		todoList.addTasks(tasks);
		assertArrayEquals(new Task[]{task1, task2, task3}, todoList.getTasksByPriority());	
	}
	
	
	@Test
	public void testGetTasksByPriority() {
		TODOList todoList = new TODOList(tasks);
		assertArrayEquals(new Task[]{task1, task2, task3}, todoList.getTasksByPriority());	
	}
	
	@Test
	public void testGetTasksInProcess() {
		TODOList todoList = new TODOList(tasks);
		assertArrayEquals(new Task[]{task2}, todoList.getTasksInProcess());
		Task task4 = new Task("rewq", "trewq", Statuses.IN_PROCESS, 5, LocalDate.of(2017, 3, 3));
		todoList.add(task4);		
		assertArrayEquals(new Task[]{task4, task2}, todoList.getTasksInProcess());
	}
	
	@Test
	public void testGetTasksForThreeDays() {
		TODOList todoList = new TODOList(tasks);
		Task task4 = new Task("rewq", "trewq", Statuses.IN_PROCESS, 1, LocalDate.now().plusDays(3));
		Task task5 = new Task("rewq", "trewq", Statuses.IN_PROCESS, 2, LocalDate.now().plusDays(4));
		todoList.addTasks(new Task[]{task4, task5});
		assertArrayEquals(new Task[]{task3, task2, task4}, todoList.getTasksForThreeDays());
	}
}
