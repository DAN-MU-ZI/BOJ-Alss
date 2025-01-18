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
		int E = Integer.parseInt(st.nextToken());

		List<int[]>[] graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new int[] {b, c});
			graph[b].add(new int[] {a, c});
		}

		st = new StringTokenizer(br.readLine());
		int V1 = Integer.parseInt(st.nextToken());
		int V2 = Integer.parseInt(st.nextToken());

		Solution solution = new Solution(N, E, graph, V1, V2);
		int result = solution.solve();
		sb.append(result == INF ? -1 : result);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private int N;
		private int E;
		private List<int[]>[] graph;
		private int V1;
		private int V2;

		Solution(int N, int E, List<int[]>[] graph, int V1, int V2) {
			this.N = N;
			this.E = E;
			this.graph = graph;
			this.V1 = V1;
			this.V2 = V2;
		}

		public int solve() {
			int[] fromStart = getDistances(1);
			int[] fromV1 = getDistances(V1);
			int[] fromV2 = getDistances(V2);

			int start_V1_V2_n, start_V2_V1_n;
			if (fromStart[V1] == INF || fromV1[V2] == INF || fromV2[N] == INF) {
				start_V1_V2_n = INF;
			} else {
				start_V1_V2_n = fromStart[V1] + fromV1[V2] + fromV2[N];
			}

			if (fromStart[V2] == INF || fromV2[V1] == INF || fromV1[N] == INF) {
				start_V2_V1_n = INF;
			} else {
				start_V2_V1_n = fromStart[V2] + fromV2[V1] + fromV1[N];
			}

			return Math.min(start_V1_V2_n, start_V2_V1_n);
		}

		public int[] getDistances(int start) {
			int[] nodes = new int[N + 1];
			Arrays.fill(nodes, INF);
			boolean[] visited = new boolean[N + 1];

			Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s[1]));

			pq.add(new int[] {start, 0});
			nodes[start] = 0;

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
						pq.add(new int[] {nextNode, nodes[nextNode]});
					}
				}
			}

			return nodes;
		}
	}
}