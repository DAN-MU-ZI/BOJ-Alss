import java.io.*;
import java.util.*;

class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Solution solution = new Solution(N);
        boolean isSKwin = solution.solve();
        sb.append(isSKwin ? "SK" : "CY");

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

        public boolean solve() {
            boolean[][] dp = new boolean[N + 1][2];
            dp[0][0] = false; dp[1][0] = true;  
            dp[0][1] = true;  dp[1][1] = false; 

            if (N >= 2) {
                dp[2][0] = false;
                dp[2][1] = true;
            }
            if (N >= 3) {
                dp[3][0] = true;
                dp[3][1] = false;
            }
            if (N >= 4) {
                dp[4][0] = true;
                dp[4][1] = false;
            }

            

            for (int i = 4; i <= N; i++) {
                dp[i][0] = dp[i - 1][1] || dp[i - 3][1] || dp[i - 4][1];
                dp[i][1] = !dp[i][0];
            }

            return dp[N][0];
        }
    }
}
