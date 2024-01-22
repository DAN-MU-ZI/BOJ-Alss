import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String line = br.readLine();
			char[] arr = line.toCharArray();

			if (line.charAt(0) == '.')
				break;

			Stack<Character> stk = new Stack<>();
			boolean result = true;
			for (int i = arr.length - 1; i >= 0; i--) {
				char c = arr[i];
				if (c == '(' || c == ')' || c == '[' || c == ']') {
					if (stk.isEmpty()) {
						if (c == ')' || c == ']')
							stk.push(c);
						else {
							result = false;
							break;
						}
					} else {
						if ((stk.peek() == ')' && c == '[') || stk.peek() == ']' && c == '(') {
							result = false;
							break;
						} else {
							if ((c == '(' && stk.peek() == ')') || (c == '[' && stk.peek() == ']'))
								stk.pop();
							else
								stk.push(c);
						}
					}
				}
			}
			if (!stk.isEmpty())
				result = false;

			sb
				.append(result ? "yes" : "no")
				.append("\n");
		}
		System.out.println(sb);
	}
}