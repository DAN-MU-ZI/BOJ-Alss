import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<String> stk = new Stack<>();
		StringBuilder sb = new StringBuilder();
		while (N-- > 0) {
			String[] split = br
				.readLine()
				.split(" ");
			switch (split[0]) {
				// push X: 정수 X를 스택에 넣는 연산이다.
				case "push":
					stk.push(split[1]);
					break;
				// pop: 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
				case "pop":
					if (stk.isEmpty())
						sb
							.append(-1)
							.append("\n");
					else
						sb
							.append(stk.pop())
							.append("\n");
					break;
				// size: 스택에 들어있는 정수의 개수를 출력한다.
				case "size":
					sb
						.append(stk.size())
						.append("\n");
					break;
				// empty: 스택이 비어있으면 1, 아니면 0을 출력한다.
				case "empty":
					sb
						.append(stk.isEmpty() ? 1 : 0)
						.append("\n");
					break;
				// top: 스택의 가장 위에 있는 정수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
				case "top":
					if (stk.isEmpty())
						sb
							.append(-1)
							.append("\n");
					else
						sb
							.append(stk.peek())
							.append("\n");
					break;
			}
		}
		System.out.println(sb);
	}
}