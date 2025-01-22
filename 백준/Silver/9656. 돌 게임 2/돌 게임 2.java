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
            return N % 2 == 0;
        }
    }
}
