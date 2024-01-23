import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Stack<Integer> stk = new Stack<>();
		for (char c : br
			.readLine()
			.toCharArray()) {
			int num = c - '0';
			while (!stk.isEmpty() && K > 0 && stk.peek() < num) {
				stk.pop();
				K--;
			}
			stk.push(num);
		}

		while (K-- > 0) {
			stk.pop();
		}
		StringBuilder sb = new StringBuilder();
		for (int i : stk) {
			sb.append(i);
		}
		System.out.println(sb);
	}
}