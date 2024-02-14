import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			char[] charArray = br
				.readLine()
				.toCharArray();
			Stack<Character> stk = new Stack<>();
			Stack<Character> tmp = new Stack<>();

			final char BACKSPACE = '-';
			final char LEFT_ARROW = '<';
			final char RIGHT_ARROW = '>';
			for (char c : charArray) {
				switch (c) {
					case BACKSPACE:
						if (!stk.isEmpty())
							stk.pop();
						break;
					case LEFT_ARROW:
						if (!stk.isEmpty())
							tmp.push(stk.pop());
						break;
					case RIGHT_ARROW:
						if (!tmp.isEmpty())
							stk.push(tmp.pop());
						break;
					default:
						stk.push(c);
						break;
				}
			}

			stk.forEach(sb::append);

			Collections.reverse(tmp);
			tmp.forEach(sb::append);

			sb.append("\n");
		}
		System.out.println(sb);
	}
}