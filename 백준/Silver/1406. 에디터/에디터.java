import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Node<Character> head = new Node<>();
		Node<Character> tail = new Node<>();

		head.setNext(tail);
		tail.setPrev(head);

		Node<Character> cursor = tail;
		for (char c : br.readLine().toCharArray()) {
			Node<Character> node = new Node<>(c);
			Node<Character> prev = cursor.getPrev();

			if (prev != null) {
				prev.setNext(node);
				node.setPrev(prev);
			}
			node.setNext(cursor);
			cursor.setPrev(node);
		}

		int N = Integer.parseInt(br.readLine());
		Node<Character> targetNode;
		for (int i = 0; i < N; i++) {
			String[] split = br.readLine().split(" ");
			switch (split[0]) {
				case "L":
					if (cursor.getPrev() != head)
						cursor = cursor.getPrev();
					break;
				case "D":
					if (cursor.getNext() != null)
						cursor = cursor.getNext();
					break;
				case "B":
					targetNode = cursor.getPrev();
					if (targetNode != head) {
						Node<Character> leftNode = targetNode.getPrev();
						leftNode.setNext(cursor);
						cursor.setPrev(leftNode);
					}
					break;
				case "P":
					Node<Character> node = new Node<>(split[1].charAt(0));
					targetNode = cursor.getPrev();
					targetNode.setNext(node);
					node.setPrev(targetNode);
					node.setNext(cursor);
					cursor.setPrev(node);
					break;
			}
		}

		print(head, tail);
	}

	private static void print(Node<Character> head, Node<Character> tail) {
		Node<Character> cursor;
		StringBuilder sb = new StringBuilder();
		cursor = head.getNext();
		while (cursor != tail) {
			sb.append(cursor.getData());
			cursor = cursor.getNext();
		}
		System.out.println(sb);
	}
}

class Node<E> {
	E data;
	Node<E> next;
	Node<E> prev;

	public Node() {
	}

	public Node(E data) {
		this.data = data;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node<E> getNext() {
		return next;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public Node<E> getPrev() {
		return prev;
	}

	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}
}

