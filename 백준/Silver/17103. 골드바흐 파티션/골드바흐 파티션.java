import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		InputHandler inputHandler = new InputHandler();
		OutputHandler outputHandler = new OutputHandler();

		Solution solution = new Solution();

		int T = inputHandler.readInt();
		while (T-- > 0) {
			int N = inputHandler.readInt();
			int result = solution.solve(N);
			outputHandler.write(result);
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

		public void write(int result) {
			write(String.valueOf(result));
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
		private final int SIZE = 1_000_000;
		private final boolean[] sieve;

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
		}

		public int solve(int n) {
			int answer = 0;
			for (int i = 2; i <= n / 2; i++) {
				if (!sieve[i] && !sieve[n - i])
					answer++;
			}
			return answer;
		}
	}
}