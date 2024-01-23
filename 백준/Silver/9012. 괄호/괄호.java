import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			String result = "YES";
			Stack<Character> stk = new Stack<>();
			for (char c : br
				.readLine()
				.toCharArray()) {
				stk.push(c);
			}

			Stack<Character> tmp = new Stack<>();
			while (!stk.isEmpty()) {
				Character pop = stk.pop();
				if (tmp.isEmpty()) {
					if (pop == '(') {
						result = "NO";
						break;
					}
					tmp.push(pop);
				} else {
					if (pop == '(' && tmp.peek() == ')')
						tmp.pop();
					else
						tmp.push(pop);
				}
			}

			if (!tmp.isEmpty())
				result = "NO";

			sb
				.append(result)
				.append("\n");
		}
		System.out.println(sb);
	}
}