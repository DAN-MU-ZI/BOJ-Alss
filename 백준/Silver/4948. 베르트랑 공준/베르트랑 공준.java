import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		InputHandler inputHandler = new InputHandler();
		OutputHandler outputHandler = new OutputHandler();

		Solution solution = new Solution();

		while (true) {
			int n = inputHandler.readInt();
			if (n == 0)
				break;
			int result = solution.solve(n);
			outputHandler.write(String.valueOf(result));
		}

		outputHandler.flush();
		outputHandler.close();
		inputHandler.close();
	}

	private static class InputHandler {
		private final BufferedReader br;
		private StringTokenizer st;

		public InputHandler() {
			this.br = new BufferedReader(new InputStreamReader(System.in));
		}

		public int readInt() throws IOException {
			if (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
			}
			return Integer.parseInt(st.nextToken());
		}

		public void close() throws IOException {
			br.close();
		}
	}

	private static class OutputHandler {
		private final BufferedWriter bw;
		private final StringBuilder sb;

		public OutputHandler() {
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			sb = new StringBuilder();
		}

		public void write(String result) {
			sb.append(result).append("\n");
		}

		public void flush() throws IOException {
			bw.write(sb.toString());
		}

		public void close() throws IOException {
			bw.flush();
			bw.close();
		}
	}

	private static class Solution {
		private final int SIZE = 123_456 * 2;
		private final boolean[] sieve;
		private final int[] dp;

		Solution() {
			boolean[] sieve = new boolean[SIZE + 1];
			sieve[0] = true;
			sieve[1] = true;

			for (int i = 2; i <= SIZE; i++) {
				for (int j = i * 2; j <= SIZE; j += i) {
					sieve[j] = true;
				}
			}
			this.sieve = sieve;

			int[] dp = new int[SIZE + 1];
			for (int i = 1; i <= SIZE; i++) {
				dp[i] = dp[i - 1] + (sieve[i] ? 0 : 1);
			}
			this.dp = dp;
		}

		public int solve(int n) {
			return dp[2 * n] - dp[n];
		}
	}
}