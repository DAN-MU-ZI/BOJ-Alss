import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int answer = 0;
		while (N-- > 0) {
			if (func(br))
				answer++;
		}
		System.out.println(answer);
	}

	private static boolean func(BufferedReader br) throws IOException {
		char[] charArray = br
			.readLine()
			.toCharArray();

		Stack<Character> stk = new Stack<>();
		for (char c : charArray) {
			if (stk.isEmpty()) {
				stk.push(c);
			} else {
				if (stk.peek() == c) {
					stk.pop();
				} else {
					stk.push(c);
				}
			}
		}
		return stk.isEmpty();
	}
}