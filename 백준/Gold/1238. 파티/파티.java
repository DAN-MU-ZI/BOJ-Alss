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
			int[] fromX = dijkstra(this.graph, X);

			List<int[]>[] reversed = reversed(this.graph);
			int[] toX = dijkstra(reversed, X);

			int answer = 0;
			for (int i = 1; i <= N; i++) {
				answer = Math.max(answer, fromX[i] + toX[i]);
			}
			return answer;
		}

		private List<int[]>[] reversed(List<int[]>[] graph) {
			List<int[]>[] newGraph = new ArrayList[N + 1];
			for (int i = 0; i <= N; i++) {
				newGraph[i] = new ArrayList<>();
			}

			for (int i = 0; i <= N; i++) {
				for (int[] edge : graph[i]) {
					int j = edge[0];
					int weight = edge[1];

					newGraph[j].add(new int[] {i, weight});
				}
			}
			return newGraph;
		}

		private int[] dijkstra(List<int[]>[] graph, int start) {
			Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s[1]));
			int[] nodes = new int[N + 1];
			boolean[] visited = new boolean[N + 1];

			Arrays.fill(nodes, Integer.MAX_VALUE);
			nodes[start] = 0;
			pq.add(new int[] {start, 0});
			while (!pq.isEmpty()) {
				int[] current = pq.poll();
				int node = current[0];
				int cost = current[1];

				if (visited[node])
					continue;
				visited[node] = true;

				for (int[] next : graph[node]) {
					int nextNode = next[0];
					int weight = next[1];

					if (!visited[nextNode] && cost + weight < nodes[nextNode]) {
						nodes[nextNode] = cost + weight;
						pq.add(new int[] {nextNode, cost + weight});
					}
				}
			}

			return nodes;
		}
	}
}