import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Deque<Integer> queue = new LinkedList<>();

		int input;
		while (true) {
			input = Integer.parseInt(br.readLine());

			if (input == -1)
				break;

			if (input == 0)
				queue.pollLast();
			else if (queue.size() != N)
				queue.addFirst(input);
		}

		if (queue.isEmpty())
			System.out.println("empty");
		else {
			while (!queue.isEmpty()) {
				System.out.print(queue.pollLast() + " ");
			}
		}
	}
}