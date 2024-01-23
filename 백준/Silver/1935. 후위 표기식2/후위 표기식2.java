import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		char[] charArray = br
			.readLine()
			.toCharArray();

		int[] converter = new int[26];
		for (int i = 0; i < N; i++) {
			converter[i] = Integer.parseInt(br.readLine());
		}

		int answer = 0;

		Stack<Double> stk = new Stack<>();
		double left, right;
		for (Character c : charArray) {
			if ('A' <= c && c <= 'Z') {
				stk.push((double)converter[c - 'A']);
			} else {
				right = stk.pop();
				left = stk.pop();
				switch (c) {
					case '+':
						stk.push(left + right);
						break;
					case '-':
						stk.push(left - right);
						break;
					case '*':
						stk.push(left * right);
						break;
					case '/':
						stk.push(left / right);
						break;
				}
			}
		}
		System.out.printf("%.2f", stk.pop());
	}
}