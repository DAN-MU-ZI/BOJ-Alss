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

	public static int solution(int N, int[][] arr) {
		int[][] dp = new int[N][1 << N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], 1000000 * 16);
		}

		dp[0][1] = 0;

		for (int mask = 1; mask < 1 << N; mask++) {
			for (int u = 0; u < N; u++) {
				if ((mask & (1 << u)) == 0)
					continue;

				for (int v = 0; v < N; v++) {
					if ((mask & (1 << v)) != 0 || arr[u][v] == 0)
						continue;

					dp[v][mask | (1 << v)] = Math.min(dp[v][mask | (1 << v)], dp[u][mask] + arr[u][v]);
				}
			}
		}

		int answer = 1000000 * 16;
		for (int i = 1; i < N; i++) {
			if (arr[i][0] != 0)
				answer = Math.min(answer, dp[i][(1 << N) - 1] + arr[i][0]);
		}
		return answer;
	}
}