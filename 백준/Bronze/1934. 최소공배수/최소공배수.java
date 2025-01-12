import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		InputHandler inputHandler = new InputHandler();
		OutputHandler outputHandler = new OutputHandler();

		int T = inputHandler.readLineAsInt();
		int[][] inputs = inputHandler.readTestCases(T);

		StringBuilder sb = new StringBuilder();
		Solution solution = new Solution();
		for (int i = 0; i < T; i++) {
			String result = solution.solve(inputs[i]);
			sb.append(result).append("\n");
		}

		outputHandler.writeOutput(sb.toString());

		outputHandler.close();
		inputHandler.close();
	}

	private static class InputHandler {
		private final BufferedReader br;

		public InputHandler() {
			this.br = new BufferedReader(new InputStreamReader(System.in));
		}

		public int readLineAsInt() throws IOException {
			return Integer.parseInt(br.readLine());
		}

		public int[][] readTestCases(int t) throws IOException {
			int[][] arr = new int[t][2];
			StringTokenizer st;
			for (int i = 0; i < t; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			return arr;
		}

		public void close() throws IOException {
			br.close();
		}
	}

	private static class OutputHandler {
		private final BufferedWriter bw;

		public OutputHandler() {
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
		}

		public void writeOutput(String string) throws IOException {
			bw.write(string);
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
		public String solve(int[] input) {
			int A = input[0];
			int B = input[1];
			int mul = gcd(A, B);
			int answer = A * B / mul;

			return String.valueOf(answer);
		}
	}
}