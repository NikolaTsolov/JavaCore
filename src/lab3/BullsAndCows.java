package lab3;

import java.util.Scanner;

public class BullsAndCows {
	
	private final String OPTIONS = "n s a";
	private int count = 0;

	private int[] bullsCows(String word, String guess) {
		int[] bullsCows = { 0, 0 };
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == guess.charAt(i)) {
				bullsCows[0] += 1;
			} else if (word.contains(guess.charAt(i) + "")) {
				bullsCows[1] += 1;
			}
		}
		return bullsCows;
	}
	
	private void showModeOptions() {
		StringBuilder sb = new StringBuilder();
		sb.append("Choose game mode between 'n' (Only numbers) ");
		sb.append("'s' (Numbers and letters and 'a' (All symbols)");
		System.out.println(sb.toString());
	}
	
	private char chooseMode(Scanner scan) {		
		showModeOptions();
		String option = scan.nextLine();
		while(!OPTIONS.contains(option)) {
			System.out.println("Wrong option");
			showModeOptions();
			option = scan.nextLine();
		}
		
		return option.charAt(0);
	}
	
	private int chooseWordLength(Scanner scan) {
		StringBuilder sb = new StringBuilder("Choose word length");
		System.out.println(sb.toString());
		String option = scan.nextLine();
		while(!option.matches("\\d+")) {
			System.out.println("Enter number");
			System.out.println(sb.toString());
			option = scan.nextLine();
		}
		
		return Integer.parseInt(option);
	}
	
	private String guessTheWord(Scanner scan, int wordLength) {
		System.out.println("Guess the word");
		String guess = scan.nextLine();
		while(guess.length() != wordLength) {
			System.out.println("Wrong word length");
			System.out.println("Guess the word");
			guess = scan.nextLine();
		}
		
		return guess;		
	}
	
	private void playTheGame(Scanner scan, RandomString rs) {
		String generatedWord = rs.generateString();
		while(true) {
			String guessedWord = guessTheWord(scan, generatedWord.length());
			count++;
			int [] bc = bullsCows(generatedWord, guessedWord);
			StringBuilder sb = new StringBuilder();
			if(bc[0] == generatedWord.length()) {
				sb.append("Congratulation you guess it in ");
				sb.append(count);
				sb.append(" tryies");
				System.out.println(sb.toString());
				break;
			}
			sb.append("You got ");
			sb.append(bc[0]);
			sb.append(" bulls and ");
			sb.append(bc[1]);
			sb.append(" cows");
			System.out.println(sb.toString());			
		}		
	}

	public void start() {
		try(Scanner scan = new Scanner(System.in)) {
			char mode = chooseMode(scan);
			RandomString rs = null;
			
			while(rs == null) {
				int length = chooseWordLength(scan);
				try {
					rs = new RandomString(length, mode);
				} catch (IllegalArgumentException ex) {
					
				}
			}
			
			playTheGame(scan, rs);			
		}
	}
}
