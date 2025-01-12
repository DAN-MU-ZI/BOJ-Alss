import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		InputHandler inputHandler = new InputHandler();
		OutputHandler outputHandler = new OutputHandler();

		int T = inputHandler.readLineAsInt();
		Solution solution = new Solution();

		for (int i = 0; i < T; i++) {
			int A = inputHandler.readInt();
			int B = inputHandler.readInt();
			int result = solution.solve(A, B);
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

		public int readLineAsInt() throws IOException {
			return Integer.parseInt(br.readLine());
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
				if (tmp == 0) break;
				a = b;
				b = tmp;
			}
			return b;
		}
		public int solve(int A, int B) {
			int mul = gcd(A, B);
			return  A * B / mul;
		}
	}
}