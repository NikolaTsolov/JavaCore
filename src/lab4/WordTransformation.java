package lab4;

public class WordTransformation {
	
	private static final char[] SHLIOKAVICA = {'a', 'b', 'w', 'g', 'd', 'e', 'v', 
			'z', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 
			'u', 'f', 'h', 'c', '`', '[', ']', 'y', 'x', '\\', 'q', 'A', 'B', 'W',
			'G', 'D', 'E', 'V', 
			'Z', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'R', 'S', 'T', 
			'U', 'F', 'H', 'C', '~', '{', '}', 'Y', 'X', '|', 'Q' };
	
	private static final char[] BG = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 
			'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 
			'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ь', 'ю', 'я', 'А', 'Б', 'В',
			'Г', 'Д', 'Е', 'Ж', 
			'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 
			'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'ѝ', 'Ю', 'Я' };
	
	public static String changeToBg(String inputString) {
		for (int i = 0; i < BG.length; i++) {
			inputString = inputString.replace(SHLIOKAVICA[i], BG[i]);
		}
		
		return inputString;
	}
}
