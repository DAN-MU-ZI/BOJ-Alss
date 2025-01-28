import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		Solution solution = new Solution(n);
		sb.append(solution.solve());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private final int N;

		public Solution(int N) {
			this.N = N;
		}

		public StringBuilder solve() {
			StringBuilder sb = new StringBuilder();
			sb.append("1\n0\n");
			return sb;
		}
	}
}