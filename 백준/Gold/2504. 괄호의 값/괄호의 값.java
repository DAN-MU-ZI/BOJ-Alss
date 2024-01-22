import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] charArray = br
			.readLine()
			.toCharArray();

		int answer = 0;
		int tmp = 1;
		Stack<Character> stk = new Stack<>();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (c == '(') {
				tmp *= 2;
				stk.push(c);
			} else if (c == '[') {
				tmp *= 3;
				stk.push(c);
			} else if (c == ')') {
				if (stk.isEmpty() || stk.peek() == '[') {
					answer = 0;
					break;
				}

				if (charArray[i - 1] == '(')
					answer += tmp;
				stk.pop();
				tmp /= 2;
			} else {
				if (stk.isEmpty() || stk.peek() == '(') {
					answer = 0;
					break;
				}

				if (charArray[i - 1] == '[')
					answer += tmp;
				stk.pop();
				tmp /= 3;
			}
		}

		if (!stk.isEmpty())
			answer = 0;

		System.out.println(answer);
	}
}