import java.io.*;
import java.util.*;

class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

        long N = Long.parseLong(br.readLine());
        Solution solution = new Solution(N);
        boolean isSKwin = solution.solve();
        sb.append(isSKwin ? "SK" : "CY");

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
        private final long N;

        public Solution(long N) {
            this.N = N;
        }

        public boolean solve() {
            boolean[] dp = new boolean[4];
            dp[1] = true;
            dp[3] = true;

            return dp[(int)(N % 4)];
        }
    }
}
