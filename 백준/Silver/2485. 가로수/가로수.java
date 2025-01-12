import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		InputHandler inputHandler = new InputHandler();
		OutputHandler outputHandler = new OutputHandler();

		Solution solution = new Solution();

		int N = inputHandler.readInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = inputHandler.readInt();
		}

		int result = solution.solve(N, arr);
		outputHandler.write(String.valueOf(result));

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
		private static int gcd(int a, int b) {
			int tmp;
			while (true) {
				tmp = a % b;
				if (tmp == 0)
					break;
				a = b;
				b = tmp;
			}
			return b;
		}

		public int solve(int N, int[] input) {
			int diff = input[1] - input[0];
			for (int i = 2; i < N; i++) {
				int diff1 = input[i] - input[i-1];
				diff = gcd(diff, diff1);
			}

			return (input[N - 1] - input[0]) / diff + 1 - N;
		}
	}
}