import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		Stack<Integer> stk = new Stack<>();
		int count = 1;
		while (n-- > 0) {
			int num = Integer.parseInt(br.readLine());
			while (count <= num) {
				stk.push(count);
				count++;
				sb.append("+\n");
			}
			if (stk.peek() != num) {
				System.out.println("NO");
				return;
			}
			stk.pop();
			sb.append("-\n");
		}
		System.out.println(sb);
	}
}