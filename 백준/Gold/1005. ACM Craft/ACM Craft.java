import java.io.*;
import java.util.*;

public class Main {
	static int T, N, K, W;
	static int[] costs;
	static List<Integer>[] rev;

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
			rev = new ArrayList[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				costs[j] = Integer.parseInt(st.nextToken());
				rev[j] = new ArrayList<>();
			}

			for (int j = 0; j < K; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				rev[b].add(a);
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
		int[] prevs = new int[N + 1];

		Queue<int[]> dq = new ArrayDeque<>();

		dq.add(new int[] {W, costs[W]});
		prevs[W] = costs[W];

		List<Integer> ends = new ArrayList<>();
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int node = cur[0];
			int cost = cur[1];

			if (cost < prevs[node]) {
				continue;
			}

			boolean isNext = false;
			for (int next : rev[node]) {
				int nextCost = cost + costs[next];
				if (nextCost > prevs[next]) {
					dq.add(new int[] {next, nextCost});
					prevs[next] = nextCost;
					isNext = true;
				}
			}

			if (!isNext) {
				ends.add(node);
			}
		}

		int answer = 0;
		for (int end : ends) {
			answer = Math.max(answer, prevs[end]);
		}
		return answer;
	}
}
