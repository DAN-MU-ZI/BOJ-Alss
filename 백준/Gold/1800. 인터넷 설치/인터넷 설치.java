import java.io.*;
import java.util.*;

class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		List<int[]>[] graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new int[] {b, c});
			graph[b].add(new int[] {a, c});
		}
		Solution solution = new Solution(N, P, K, graph);
		int result = solution.solve();
		sb.append(result == INF ? -1 : result);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {

		private final int N;
		private final int P;
		private final int K;
		private final List<int[]>[] graph;

		public Solution(int N, int P, int K, List<int[]>[] graph) {
			this.N = N;
			this.P = P;
			this.K = K;
			this.graph = graph;
		}

		public int solve() {
			int[][] nodes = new int[N + 1][K + 1];
			Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s[1]));

			for (int i = 0; i <= N; i++) {
				Arrays.fill(nodes[i], INF);
			}

			int start = 1;
			nodes[start][K] = 0;
			pq.add(new int[] {start, 0, K});

			while (!pq.isEmpty()) {
				int[] current = pq.poll();
				int node = current[0];
				int cost = current[1];
				int remain = current[2];

				if (cost > nodes[node][remain])
					continue;

				for (int[] next : graph[node]) {
					int nextNode = next[0];
					int weight = next[1];

					int max = Math.max(cost, weight);
					if (max < nodes[nextNode][remain]) {
						nodes[nextNode][remain] = max;
						pq.add(new int[] {nextNode, max, remain});
					}

					if (remain > 0 && cost < nodes[nextNode][remain - 1]) {
						nodes[nextNode][remain - 1] = cost;
						pq.add(new int[] {nextNode, cost, remain - 1});
					}
				}
			}

			int answer = nodes[N][0];
			for (int i = 0; i <= K; i++) {
				answer = Math.min(answer, nodes[N][i]);
			}

			return answer;
		}
	}
}