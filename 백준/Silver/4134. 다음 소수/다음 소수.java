import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		InputHandler inputHandler = new InputHandler();
		OutputHandler outputHandler = new OutputHandler();

		Solution solution = new Solution();

		int T = inputHandler.readInt();
		for (int t = 0; t < T; t++){
			long n = inputHandler.readLong();
			long result = solution.solve(n);
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

		public long readLong() throws IOException {
			if (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
			}
			return Long.parseLong(st.nextToken());
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
		public long gcd(long a, long b) {
			while (true) {
				long tmp = a %b;
				if (tmp == 0) return b;
				a = b;
				b = tmp;
			}
		}
		public long solve(long n) {
			if (n == 0) return 2;
			if (n == 1) return 2;
			if (n == 2) return 2;

			for (long i = n; i < Long.MAX_VALUE; i++){
				boolean flag = true;

				for (long j = 2; j <= (long) Math.sqrt(i); j++){
					if (i % j == 0) {
						flag = false;
						break;
					}
				}

				if (flag) return i;
			}
			return -1;
		}
	}
}