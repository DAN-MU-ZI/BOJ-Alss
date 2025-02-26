import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int weights[], balls[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		weights = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());

		balls = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			balls[i] = Integer.parseInt(st.nextToken());
		}

		boolean[] answer = solve();
		for(boolean ans: answer) {
			sb.append((ans ? "Y" : "N") + " ");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static boolean[] solve() {
		boolean[] answer = new boolean[M];
		boolean[] dp = new boolean[40001];
		dp[0] = true;

		for (int i = 0; i < N; i++) {
			int weight = weights[i];
			for (int j = 40000; j >= weight; j--) {
				if (dp[j - weight]) {
					dp[j] = true;
				}
			}
		}
		for (int i = 0; i < M; i++) {
			int ball = balls[i];
			for (int j = 0; j <= 40000 - ball; j++) {
				if (dp[j] && dp[j + ball]) {
					answer[i] = true;
					break;
				}
			}
		}

		return answer;
	}
}
