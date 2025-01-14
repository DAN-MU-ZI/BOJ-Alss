import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		Solution solution = new Solution();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int result = solution.solve(N, M);
			sb.append(result).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private final int[][] dp;

		Solution() {
			int[][] dp = new int[31][31];
			for (int i = 1; i <= 30; i++) {
				dp[1][i] = i;
			}

			for (int i = 2; i <= 30; i++) {
				for (int j = 2; j <= 30; j++) {
					dp[i][j] += dp[i][j - 1] + dp[i - 1][j - 1];
				}
			}

			this.dp = dp;
		}

		public int solve(int N, int M) {
			return this.dp[N][M];
		}
	}
}