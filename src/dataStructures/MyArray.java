package dataStructures;

public class MyArray <T> {
	
	private int size = 2;
	private Object[] arr = new Object[size];	
	private int index = 0;
	
	public void add(T value) {
		if(index > size - 1) {
			size *= 2;
			arr = new Object[size];
		}
	}
	
	
}
