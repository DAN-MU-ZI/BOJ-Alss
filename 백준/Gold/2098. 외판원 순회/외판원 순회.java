import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = solution(N, arr);

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	public static int dfs(int N, int[][] arr, int[][] dp, int cur, int visit) {
		if (visit == (1 << N) - 1) {
			if (arr[cur][0] != 0)
				return arr[cur][0];
			return 1000000 * 16;
		}

		if (dp[cur][visit] != -1)
			return dp[cur][visit];

		int cost = 1000000 * 16;

		for (int i = 0; i < N; i++) {
			if ((visit & (1 << i)) == 0 && arr[cur][i] > 0) {
				cost = Math.min(cost, dfs(N, arr, dp, i, visit | (1 << i)) + arr[cur][i]);
			}
		}

		dp[cur][visit] = cost;
		return cost;
	}

	public static int solution(int N, int[][] arr) {
		int[][] dp = new int[N][1 << N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}

		return dfs(N, arr, dp, 0, 1);
	}
}