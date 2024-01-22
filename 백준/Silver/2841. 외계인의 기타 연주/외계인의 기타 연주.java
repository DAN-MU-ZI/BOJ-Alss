import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		Stack<Integer>[] stacks = new Stack[N + 1];
		for (int i = 0; i < N + 1; i++) {
			stacks[i] = new Stack<>();
		}

		int answer = 0;
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());

			if (stacks[src].isEmpty()) {
				stacks[src].push(dst);
				answer++;
			} else {
				while (!stacks[src].isEmpty() && dst < stacks[src].peek()) {
					stacks[src].pop();
					answer++;
				}
				if (stacks[src].isEmpty()) {
					stacks[src].push(dst);
					answer++;
				} else if (dst != stacks[src].peek()) {
					stacks[src].push(dst);
					answer++;
				}
			}
		}
		System.out.println(answer);
	}
}