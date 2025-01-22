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
            boolean[] dp = new boolean[N + 1];

            dp[0] = true;
            dp[1] = false;
            if (N >= 2) dp[2] = true;
            if (N >= 3) dp[3] = false;
            if (N >= 4) dp[4] = true;

            for (int i = 4; i <= N; i++) {
                dp[i] = !(dp[i - 4] && dp[i - 3] && dp[i - 1]);
            }

            return dp[N];
        }
    }
}
