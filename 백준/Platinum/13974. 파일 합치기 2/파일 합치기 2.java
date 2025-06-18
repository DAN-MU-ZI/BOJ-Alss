import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int K = Integer.parseInt(br.readLine());
			int[] files = new int[K + 1];
			int[] prefixSum = new int[K + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				files[i] = Integer.parseInt(st.nextToken());
				prefixSum[i] = prefixSum[i - 1] + files[i];
			}

			int[][] dp = new int[K + 2][K + 2];
			int[][] opt = new int[K + 2][K + 2];

			for (int i = 1; i <= K; i++) {
				opt[i][i] = i;
			}

			for (int len = 2; len <= K; len++) {
				for (int i = 1; i <= K - len + 1; i++) {
					int j = i + len - 1;
					dp[i][j] = Integer.MAX_VALUE;

					int start = opt[i][j - 1];
					int end = opt[i + 1][j];

					for (int k = start; k <= end; k++) {
						int cost = dp[i][k] + dp[k + 1][j] + prefixSum[j] - prefixSum[i - 1];
						if (cost < dp[i][j]) {
							dp[i][j] = cost;
							opt[i][j] = k;
						}
					}
				}
			}
			sb.append(dp[1][K]).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
	}
}
