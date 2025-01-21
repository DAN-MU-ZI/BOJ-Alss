import java.io.*;
import java.util.*;

class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] A = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Solution solution = new Solution(n, A);
        int answer = solution.solve();
        sb.append(answer);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
        private final int n;
        private final int[][] A;

        public Solution(int n, int[][] A) {
            this.n = n;
            this.A = A;
        }

        public int solve() {
            int[][]dp = new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(dp[i], INF);
            }

            dp[0][0] = 0;

            for (int i = 1; i < n; i++) {
                dp[0][i] = Math.min(dp[0][i], dp[0][i-1] + Math.max(0, A[0][i] - A[0][i-1] + 1));
                dp[i][0] = Math.min(dp[i][0], dp[i-1][0] + Math.max(0, A[i][0] - A[i-1][0] + 1));
            }

            for (int i = 1; i < n; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = Math.min(dp[i][j], Math.min(dp[i][j-1] + Math.max(0, A[i][j] - A[i][j-1] + 1), dp[i-1][j] + Math.max(0, A[i][j] - A[i-1][j] + 1)));
                }
            }
            
            return dp[n - 1][n - 1];
        }
    }       
}