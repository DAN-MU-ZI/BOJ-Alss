import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		long[][] dp = new long[11][2001];

		for (int j = 0; j < 2001; j++) {
			dp[0][j] = 1;
		}

		for (int i = 1; i < 11; i++) {
			for (int j = 1; j < 2001; j++) {
				dp[i][j] = dp[i - 1][j / 2] + dp[i][j - 1];
			}
		}

		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			sb
				.append(dp[n][m])
				.append("\n");
		}
		System.out.println(sb);
	}
}