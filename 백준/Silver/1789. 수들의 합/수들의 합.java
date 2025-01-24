import java.io.*;
import java.util.*;

class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
        long S = Long.parseLong(br.readLine());
        Solution solution = new Solution(S);
        long answer = solution.solve();
        sb.append(answer);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

    private static class Solution {
        private final long S;

        public Solution(long S) {
            this.S = S;
        }

        public long solve() {
            long n = 0;
            while (true) {
                n++;
                if ( n * (n + 1) / 2 > S) return n - 1;
            }
        }
    }
}
