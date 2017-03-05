package lab5;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import lab2.TODOList;
import lab2.Task;
import lab2.Task.Statuses;

public class UserInteractior {

	private String menu = new StringBuilder().append("Изберете опция:\n")
			.append("1) Всички задачи подредени по приоритет\n").append("2) Задачи със статус IN PROCESS\n")
			.append("3) Задачи, които да се завършат в следващите три дни\n")
			.append("4) Импортиране на задачи от файл\n").append("5) Експортиране на задачи във файл\n")
			.append("6) Добавяне на задача\n").append("7) Изход" + "\n").toString();

	private int takeInput(Scanner sc) {
		showManue();
		promptUser();
		String line = null;

		while (!(line = sc.nextLine()).matches("\\d+")) {
			System.out.println("Въведеният вход е невалиден опитайте отново");
			showManue();
			promptUser();
		}

		return Integer.parseInt(line);
	}

	private String takeFilename(Scanner sc) {
		System.out.println("Въведете пълния път до файлът");
		String line = sc.nextLine();
		return line;
	}

	private void showManue() {
		System.out.println(menu);
	}

	private void promptUser() {
		System.out.println("Вашият избор (1-7):");
	}

	private TODOList todoList;

	private void fillTODOLIST(Scanner sc) {
		List<Task> tasks = null;
		while (tasks == null) {
			try {
				tasks = FileManipulator.CSVRead(takeFilename(sc));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}

		if (todoList == null) {
			todoList = new TODOList(tasks.toArray(new Task[tasks.size()]));
		} else {
			todoList.addTasks(tasks.toArray(new Task[tasks.size()]));
		}
	}

	private void addTask(Scanner sc) {
		if (todoList == null) {
			todoList = new TODOList();
		}

		todoList.add(new Task(chooseTitle(sc), chooseDescription(sc), chooseStatus(sc), choosePriority(sc),
				chooseDeadline(sc)));
	}

	private String chooseTitle(Scanner sc) {
		System.out.println("Напиши заглавие на задачата");
		String title = null;

		while ((title = sc.nextLine()) == null) {
			System.out.println("Напиши заглавие на задачата");
		}

		return title;
	}

	private String chooseDescription(Scanner sc) {
		System.out.println("Въведи описание на задачата");

		String description = null;

		while ((description = sc.nextLine()) == null) {
			System.out.println("Въведи описание на задачата");
		}

		return description;
	}

	private Statuses chooseStatus(Scanner sc) {
		System.out.println("Въведете статусът на задачата " + "('INITIAL', 'IN_PROCESS', 'DONE')");
		String status = null;
		String realStatuses = "INITIAL IN_PROCESS DONE";

		while (!realStatuses.contains(status = sc.nextLine())) {
			System.out.println("Въведете статусът на задачата " + "('INITIAL', 'IN_PROCESS', 'DONE')");
		}

		return Statuses.valueOf(status);
	}

	private int choosePriority(Scanner sc) {
		System.out.println("Въведи приоритет на задачата");
		String priority = null;

		while (!(priority = sc.nextLine()).matches("\\d++")) {
			System.out.println("Въведи приоритет на задачата");
		}

		return Integer.parseInt(priority);
	}

	private LocalDate chooseDeadline(Scanner sc) {
		String date = null;
		String[] yearMonthDay = null;
		int year;
		int month;
		int day;

		while (true) {
			System.out.println("Въведи крайна дата за изпълнението във формат 'г м д' (2017 3 1)");
			date = sc.nextLine();
			yearMonthDay = date.split(" ");

			if (yearMonthDay.length == 3 && yearMonthDay[0].matches("\\d+") && yearMonthDay[1].matches("\\d+")
					&& yearMonthDay[2].matches("\\d+")) {
				year = Integer.parseInt(yearMonthDay[0]);
				month = Integer.parseInt(yearMonthDay[1]);
				day = Integer.parseInt(yearMonthDay[2]);

				if (year > 0 && month > 0 && month <= 12 && day > 0 && day <= 31) {
					break;
				}
			}
		}

		return LocalDate.of(year, month, day);
	}

	// private void showAllTasks(Task[] tasks) {
	// for (Task task : tasks) {
	// System.out.println(task);
	// }
	// }

	private Task[] getResultArray(TODOList toDoListSearcher, int chosenOption) {
		Task[] resultTasksArray = null;
		switch (chosenOption) {
		case 1: {
			resultTasksArray = toDoListSearcher.getTasksByPriority();
			break;
		}

		case 2: {
			resultTasksArray = toDoListSearcher.getTasksInProcess();
			break;
		}

		case 3: {
			resultTasksArray = toDoListSearcher.getTasksForThreeDays();
			break;
		}
		}

		return resultTasksArray;
	}
	
	private void saveToFile(Scanner sc) {
		if(todoList == null) {
			throw new NullPointerException();
		}
		
		boolean isFinished = false;
		while(!isFinished) {
			try {
				FileManipulator.CSVWriter(getFilename(sc), todoList.getTasks().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private String getFilename(Scanner sc) {
		System.out.println("Въведи име на файла");
		String filename = null;
		
		while((filename = sc.nextLine()) == null) {
			System.out.println("Въведи име на файла");
		}
		
		return filename;
	}

	private void savingTasksOptions(Scanner sc, int chosenOption) {
		switch (chosenOption) {
		case 4: {
			fillTODOLIST(sc);
			break;
		}
		case 5: {
			try {
				saveToFile(sc);
				break;
			} catch (NullPointerException e) {
				System.out.println("Списъкът с задачи е празен не можете да експортирате");
				break;
			}
		}
		case 6: {
			addTask(sc);
			break;
		}
		}
	}

	private boolean gettingTasksOptions(int chosenOption) {
		boolean exitStatus = false;

		if (todoList == null) {
			throw new NullPointerException();
		}

		final TODOList toDoListSearcher = new TODOList(todoList.getTasks());
		Task[] resultArray = getResultArray(toDoListSearcher, chosenOption);
		if (Objects.nonNull(resultArray)) {
			System.out.println(Arrays.toString(resultArray));
		} else {
			exitStatus = true;
		}

		return exitStatus;
	}

	public void start() {
		boolean exitStatus = false;

		try (Scanner sc = new Scanner(System.in)) {
			while (!exitStatus) {
				int chosenOption = takeInput(sc);
				try {
					if (chosenOption < 1 || chosenOption > 7) {
						throw new IllegalArgumentException();
					}

					if (chosenOption == 4 || chosenOption == 6) {
						savingTasksOptions(sc, chosenOption);
					} else {
						exitStatus = gettingTasksOptions(chosenOption);
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Невалидна опция. Избери пак!");
				}

			}

			System.out.println("Излезохте от програмата!");
		}
	}

}
