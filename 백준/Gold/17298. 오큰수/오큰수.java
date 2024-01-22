import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Stack<Integer> stk = new Stack<>();
		stk.push(0);

		int[] answer = new int[N];

		for (int i = 1; i < N; i++) {
			while (!stk.isEmpty() && arr[stk.peek()] < arr[i])
				answer[stk.pop()] = arr[i];
			stk.push(i);
		}

		StringBuilder sb = new StringBuilder();
		for (int n : answer) {
			sb
				.append(n == 0 ? -1 : n)
				.append(" ");
		}
		System.out.println(sb);
	}
}