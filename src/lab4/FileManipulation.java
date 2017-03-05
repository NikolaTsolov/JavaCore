package lab4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class FileManipulation {

	private String readFile(String path) throws FileNotFoundException, IOException {
		String line = null;
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			
			return sb.toString();
		}
	}
	
	private void writeFile(String path, String content) throws IOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
			bw.write(content);
		}
	}
	
	public void printMap(Map<Character, Integer> map) {
		for(Map.Entry<Character, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
	
	public void printWordMap(Map<String, Integer> map) {
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
	
	public void start(String readFile, String writeFile) {
			try {
				String text = readFile(readFile);
				writeFile(writeFile, WordTransformation.changeToBg(text));
				text = readFile(writeFile);
				String [] words = text.split(" ");
				System.out.println(words.length);
//				Analiz analiz = new Analiz();
//				analiz.fillTheMapWithChars(text);
//				printMap(analiz.sortTheMap());		
				Analiz analiz = new Analiz(words.length);
				analiz.fillTheMapWithWords(words);
				printWordMap(analiz.sortTheWordMap());		
				
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
				catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}

}
