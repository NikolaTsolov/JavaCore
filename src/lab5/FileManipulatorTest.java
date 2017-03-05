package lab5;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class FileManipulatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCSVWriter() {
//			FileManipulator.CSVWriter("/home/nikola/Tasks.csv", "phpphp");
			try {
//				FileManipulator.CSVWriter("/home/nikola/asdf/asdf.txt", "try");
				FileManipulator.CSVWriter("/home/nikola/Tasks/Tasks.csv", "Another try");
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
