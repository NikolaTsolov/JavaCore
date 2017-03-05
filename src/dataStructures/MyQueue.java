package dataStructures;

public class MyQueue<T> {

	private class Node<T> {
		private T value;
		private Node<T> next;

		Node(T value, Node<T> next) {
			this.setValue(value);
			this.setNext(next);
		}

		public T getValue() {
			return value;
		}

		public void setValue(T value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}
	}

	private Node<T> start;
	private Node<T> next;

	public MyQueue() {
		start = new Node<T>(null, null);
	}

	public void add(T value) {
		if (next == null) {
			next = new Node<T>(value, null);
			start.setNext(next);
		} else {
			Node<T> tmp = new Node<T>(value, null);
			next.setNext(tmp);
			next = tmp;
		}
	}

	public T poll() {
		if (start.next != null) {
			Node<T> tmp = start.next;
			start = start.next;

			return tmp.value;
		}

		return null;
	}

}
