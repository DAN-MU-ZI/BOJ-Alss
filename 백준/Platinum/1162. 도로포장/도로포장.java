import java.io.*;
import java.util.*;

class Main {
	private static final long INF = Long.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		List<long[]>[] graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new long[] {b, c});
			graph[b].add(new long[] {a, c});
		}

		Solution solution = new Solution(N, M, K, graph);
		long result = solution.solve();
		sb.append(result);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private final int N;
		private final int M;
		private final int K;
		private final List<long[]>[] graph;

		Solution(int N, int M, int K, List<long[]>[] graph) {
			this.N = N;
			this.M = M;
			this.K = K;
			this.graph = graph;
		}

		public long solve() {
			long[][] nodes = new long[N + 1][K + 1];
			Queue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(s -> s[1]));

			init(nodes, pq);

			while (!pq.isEmpty()) {
				long[] current = pq.poll();
				int node = (int)current[0];
				long cost = current[1];
				int remain = (int)current[2];

				if (cost > nodes[node][remain])
					continue;

				for (long[] next : graph[node]) {
					int nextNode = (int)next[0];
					int weight = (int)next[1];

					if (cost + weight < nodes[nextNode][remain]) {
						nodes[nextNode][remain] = cost + weight;
						pq.add(new long[] {nextNode, cost + weight, remain});
					}
					if (remain > 0 && cost < nodes[nextNode][remain - 1]) {
						nodes[nextNode][remain - 1] = cost;
						pq.add(new long[] {nextNode, cost, remain - 1});
					}
				}
			}

			long answer = INF;
			for (long cost : nodes[N]) {
				answer = Math.min(answer, cost);
			}
			return answer;
		}

		private void init(long[][] nodes, Queue<long[]> pq) {
			int start = 1;
			for (int i = 0; i <= N; i++) {
				Arrays.fill(nodes[i], INF);
			}
			nodes[start][K] = 0;
			pq.add(new long[] {start, 0, K});
		}
	}
}