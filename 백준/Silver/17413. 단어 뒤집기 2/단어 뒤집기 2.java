import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder answer = new StringBuilder();
		Deque<Character> stk = new LinkedList<>();
		boolean isTag = false;
		for (char c : br
			.readLine()
			.toCharArray()) {
			if (c == '<') {
				while (!stk.isEmpty()) {
					answer.append(stk.poll());
				}
				isTag = true;
			} else if (c == '>') {
				answer.append('<');
				while (!stk.isEmpty()) {
					answer.append(stk.poll());
				}
				answer.append('>');
				isTag = false;
			} else if (c == ' ') {
				if (isTag) {
					stk.offerLast(c);
				} else {
					while (!stk.isEmpty()) {
						answer.append(stk.poll());
					}
					answer.append(' ');
				}
			} else {
				if (isTag)
					stk.offerLast(c);
				else
					stk.offerFirst(c);
			}
		}
		while (!stk.isEmpty()) {
			answer.append(stk.poll());
		}
		System.out.println(answer);
	}
}