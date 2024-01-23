import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder result = new StringBuilder();
		while (T-- > 0) {
			result
				.append(func(br))
				.append("\n");
		}
		System.out.println(result);
	}

	private static String func(BufferedReader br) throws IOException {
		char[] p = br
			.readLine()
			.toCharArray();
		int n = Integer.parseInt(br.readLine());

		String input = br.readLine();
		String[] splittedString = input
			.substring(1, input.length() - 1)
			.split(",");
		LinkedList<String> deque = new LinkedList<>(Arrays
			.asList(splittedString)
			.subList(0, n));

		boolean isForward = true;
		for (char c : p) {
			switch (c) {
				case 'R':
					isForward = !isForward;
					break;
				case 'D':
					if (deque.isEmpty())
						return "error";
					if (isForward)
						deque.pollFirst();
					else
						deque.pollLast();
					break;
			}
		}

		if (!isForward)
			Collections.reverse(deque);
		return "[" + String.join(",", deque) + "]";
	}
}