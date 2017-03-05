package lab3;

import java.util.ArrayList;
import java.util.Random;

public class RandomString {

	private char[] symbols;

	private void buildWord(char allowedSymbols) {
		StringBuilder tmp = new StringBuilder();
		switch (allowedSymbols) {
		case 'a': {
			for (char ch = '!'; ch <= '~'; ++ch)
				tmp.append(ch);
			break;
		}
		case 's': {
			for (char ch = '0'; ch <= '9'; ++ch)
				tmp.append(ch);
			for (char ch = 'a'; ch <= 'z'; ++ch)
				tmp.append(ch);
			for (char ch = 'A'; ch <= 'Z'; ++ch)
				tmp.append(ch);
			break;
		}
		case 'n': {
			for (char ch = '0'; ch <= '9'; ++ch)
				tmp.append(ch);
			break;
		}
		}

		symbols = tmp.toString().toCharArray();
	}

	private final Random random = new Random();

	private final ArrayList<Character> buf;
	
	private final int length;

	public RandomString(int length, char allowedSymbols) {
		buildWord(allowedSymbols);
		if (length < 1)
			throw new IllegalArgumentException("length < 1: " + length);
		this.length = length;
		buf = new ArrayList<>(length);
	}

	public String generateString() {
		for (int idx = 0; idx < length; ++idx) {
			char symbol = symbols[random.nextInt(symbols.length)];
			while (buf.contains(symbol)) {
				symbol = symbols[random.nextInt(symbols.length)];
			}
			buf.add(symbol);
		}
		final char[] chars = new char[length];
		for (int i = 0; i < chars.length; i++) {
			chars[i] = buf.get(i);
		}
		
		return new String(chars);
	}
}