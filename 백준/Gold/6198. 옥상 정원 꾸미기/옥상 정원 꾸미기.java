import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		Stack<Integer> stk = new Stack<>();
		long answer = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			if (stk.isEmpty()) {
				stk.push(i);
			} else {
				while (!stk.isEmpty() && arr[i] >= arr[stk.peek()]) {
					answer += i - stk.pop() - 1;
				}
				stk.push(i);
			}
		}
		while (!stk.isEmpty()) {
			answer += N - stk.pop() - 1;
		}
		System.out.println(answer);
	}
}