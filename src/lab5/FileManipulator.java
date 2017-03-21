package lab5;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import lab2.Task;
import lab2.Task.Statuses;


public class FileManipulator {

	private static final String ZIPPER_DESTINATION = "/home/nikola/Tasks/Backups/";

	public static List<Task> CSVRead(String filename) throws FileNotFoundException, IOException {
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			List<Task> tasks = new LinkedList<>();
			while ((line = br.readLine()) != null) {
				String[] fields = line.split(",");
				tasks.add(new Task(fields[0], fields[1], Statuses.valueOf(fields[2]), Integer.parseInt(fields[3]),
						LocalDate.of(Integer.parseInt(fields[4]), Integer.parseInt(fields[5]),
								Integer.parseInt(fields[6]))));
			}

			return tasks;
		}
	}

	private static boolean isWithinNHours(LocalDateTime now, LocalDateTime deadline, int timeInHours) {
		LocalDateTime newDate = deadline.plusHours(timeInHours);

		if (now.isAfter(newDate) || now.equals(newDate)) {
			return false;
		}

		return true;
	}

	private static void zipper(String filepath, String output, LocalDateTime ldt) throws IOException {

		StringBuilder destination = new StringBuilder().append(ZIPPER_DESTINATION).append(ldt.getDayOfMonth())
				.append(".").append(ldt.getMonthValue()).append(".").append(ldt.getYear());
		Path path = Paths.get(destination.toString());
		File file = path.toFile();
		file.mkdirs();
		String [] dirs = filepath.split("/");
		String filename = dirs[dirs.length - 1];
		
		try (ZipOutputStream zos = new ZipOutputStream(
				new FileOutputStream(destination.append("/Backup.zip").toString()))) {
			ZipEntry entry1 = new ZipEntry(filename);
			zos.putNextEntry(entry1);
			byte[] data = output.getBytes();
			zos.write(data, 0, data.length);
			zos.closeEntry();
		}
	}

	private static boolean createFile(File file, String output) throws IOException {
		File parent = file.getParentFile();
		if (!parent.exists()) {
			parent.mkdirs();
		}

		try (BufferedWriter br = new BufferedWriter(new FileWriter(file.toString()))) {
			br.write(output);
			return true;
		}
	}
	
	private static String fileToString(String filename) throws FileNotFoundException, IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			
			return sb.toString();
		}
	}

	public static boolean CSVWriter(String filename, String output) throws IOException {
		Path path = Paths.get(filename);
		File file = path.toFile();
		if (!Files.exists(path)) {
			return createFile(file, output);
		} else {
			FileTime ft = Files.getLastModifiedTime(path);
			LocalDateTime ldt = LocalDateTime.parse(ft.toString().substring(0, ft.toString().length() - 1));

			if (!isWithinNHours(LocalDateTime.now(), ldt, 24)) {
				String content = fileToString(path.toString());
				zipper(path.toString(), content, ldt);
				file.delete();
			}
			
			while(path.toFile().exists()) {
				String filepath = path.toString();
				path = Paths.get(filepath.substring(0, filepath.length() - 4) 
						+ 1 + filepath.substring(filepath.length() - 4, filepath.length()));
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(path.toString()))) {
				bw.write(output);
				return true;
			}
		}
	}
}
