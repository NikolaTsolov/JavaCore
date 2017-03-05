package lab4;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import lab2.Task;

public class Analiz {

	private Map<String, Integer> wordHolder;
	private Map<Character, Integer> letterHolder;
	
	public Analiz(int wordCount) {
		wordHolder = new HashMap<>(wordCount * 2);
	}
	
	public Analiz() {
		letterHolder = new HashMap<>(2 * 32);
	}
	
	public void fillTheMapWithWords(String[] words) {
		for (int i = 0; i < words.length; i++) {
			String word = words[i].toLowerCase();
			if(true) { //word.matches("[а-я]")
				if(wordHolder.containsKey(word)) {
					wordHolder.put(word, 1 + wordHolder.get(word));
				} else {
					wordHolder.put(word, 1);
				}
			}
		}
	}
	
	public void fillTheMapWithChars(String text) {
		for (int i = 0; i < text.length(); i++) {
			char letter = text.charAt(i);
			if(letter >= 'а' && letter <= 'я') {
				if(letterHolder.containsKey(letter)) {
					letterHolder.put(letter, 1 + letterHolder.get(letter));
				} else {
					letterHolder.put(letter, 1);
				}
			}
		}
	}
	
	public Map<Character, Integer> sortTheMap() {
		List<Map.Entry<Character, Integer>> list =
                new LinkedList<Map.Entry<Character, Integer>>(letterHolder.entrySet());
	
		Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2) {
            	if(o1.getValue() > o2.getValue()) {
            		return -1;
            	} else if (o1.getValue() == o2.getValue()) {
            		return 0;
            	} else {
            		return 1;
            	}          
            }
        });
		
		Map<Character, Integer> sortedMap = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;
	}
	
	public Map<String, Integer> sortTheWordMap() {
		List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(wordHolder.entrySet());
	
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
            	if(o1.getValue() > o2.getValue()) {
            		return -1;
            	} else if (o1.getValue() == o2.getValue()) {
            		return 0;
            	} else {
            		return 1;
            	}          
            }
        });
		
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;
	}
	
//	public Entry<Character, Integer> maximum() {
//		ArrayList<Integer> arr = new ArrayList<>(letterHolder.values());
//		
//	}
	
	
	
}
