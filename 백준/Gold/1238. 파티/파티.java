import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		List<int[]>[] graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());

			graph[src].add(new int[] {dst, T});
		}
		Solution solution = new Solution(N, M, X, graph);
		int answer = solution.solve();
		sb.append(answer);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {

		private final int N;
		private final int M;
		private final int X;
		private final List<int[]>[] graph;

		public Solution(int N, int M, int X, List<int[]>[] graph) {
			this.N = N;
			this.M = M;
			this.X = X;
			this.graph = graph;
		}

		public int solve() {
			int[] fromX = dijkstra(X);

			int answer = Integer.MIN_VALUE;
			for (int i = 1; i <= N; i++) {
				int[] fromI = dijkstra(i);
				answer = Math.max(answer, fromI[X] + fromX[i]);
			}

			return answer;
		}

		public int[] dijkstra(int start) {
			Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s[1]));
			int[] nodes = new int[N + 1];
			Arrays.fill(nodes, Integer.MAX_VALUE);
			nodes[start] = 0;
			pq.add(new int[] {start, 0});
			while (!pq.isEmpty()) {
				int[] current = pq.poll();
				int node = current[0];
				int cost = current[1];

				if (cost > nodes[node])
					continue;

				for (int[] next : graph[node]) {
					int nextNode = next[0];
					int weight = next[1];

					if (cost + weight < nodes[nextNode]) {
						nodes[nextNode] = cost + weight;
						pq.add(new int[] {nextNode, cost + weight});
					}
				}
			}

			return nodes;
		}
	}
}