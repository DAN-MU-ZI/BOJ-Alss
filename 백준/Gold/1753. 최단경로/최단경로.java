import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int start = Integer.parseInt(br.readLine());

		List<int[]>[] graph = new ArrayList[V+1];
		for (int i = 0; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[src].add(new int[] {dst, weight});
		}

		Solution solution = new Solution(V, E, graph, start);
		int[] result = solution.solve();
		for (int i = 1; i <= V; i++) {
			sb.append(result[i] == Integer.MAX_VALUE ? "INF" : result[i]).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private int V;
		private int E;
		private int[] nodes;
		private List<int[]>[] graph;
		private int start;

		Solution(int V, int E, List<int[]>[] graph, int start) {
			this.V = V;
			this.E = E;
			this.nodes = new int[V + 1];
			Arrays.fill(this.nodes, Integer.MAX_VALUE);
			this.graph = graph;
			this.start = start;
		}

		public int[] solve() {
			Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s[1]));

			pq.add(new int[] {start, 0});
			this.nodes[start] = 0;

			while (!pq.isEmpty()) {
				int[] current = pq.poll();
				int node = current[0];
				int cost = current[1];

				if (cost > nodes[node]) continue;

				for (int[] edge : graph[node]) {
					int nextNode = edge[0];
					int weight = edge[1];

					if (cost + weight < nodes[nextNode]) {
						nodes[nextNode] = cost + weight;
						pq.add(new int[] {nextNode, nodes[nextNode]});
					}
				}
			}

			return nodes;
		}
	}
}