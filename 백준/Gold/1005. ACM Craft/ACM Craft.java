import java.io.*;
import java.util.*;

public class Main {
	static int T, N, K, W;
	static int[] costs, dp, fanIn;
	static List<Integer>[] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			costs = new int[N + 1];
			dp = new int[N + 1];
			fanIn = new int[N + 1];
			graph = new ArrayList[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				costs[j] = Integer.parseInt(st.nextToken());
				graph[j] = new ArrayList<>();
			}

			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a].add(b);
				fanIn[b]++;
			}

			W = Integer.parseInt(br.readLine());

			int answer = solve();
			sb.append(answer + "\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static int solve() {
		Queue<Integer> dq = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {
			if (fanIn[i] == 0) {
				dq.offer(i);
				dp[i] = costs[i];
			}
		}

		while (!dq.isEmpty()) {
			int curr = dq.poll();

			for (int next : graph[curr]) {
				dp[next] = Math.max(dp[next], dp[curr] + costs[next]);
				fanIn[next]--;
				if (fanIn[next] == 0) {
					dq.offer(next);
				}
			}
		}

		return dp[W];
	}
}
