package dataStructures;

public class MyStack<T> {
	private class Node<T> {
		private T value;
		private Node<T> next;
		private Node<T> prvious;

		Node(T value, Node<T> next, Node<T> previous) {
			this.setValue(value);
			this.setNext(next);
			this.setPrevious(previous);
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

		public void setPrevious(Node<T> previous) {
			this.prvious = previous;
		}
	}

	Node<T> next;

	public MyStack() {

	}

	public void add(T value) {
		if (next == null) {
			next = new Node<T>(value, null, null);
		} else {
			Node<T> tmp = new Node<T>(value, null, next);
			next.setNext(tmp);
			next = tmp;
		}
	}

	public T pop() {
		if (next != null) {
			Node<T> tmp = next;

			if (next.prvious == null) {
				next = null;
				return tmp.value;
			}

			next = tmp.prvious;
			next.setNext(null);

			return tmp.getValue();
		}
		
		return null;
	}
}
