import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0)
				break;

			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());

			int[][] edges = new int[M][3];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int U = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());

				edges[i] = new int[] {U, V, P};
			}

			Solution solution = new Solution(N, M, S, D, edges);
			sb.append(solution.solve()).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Node {
		int node;
		int weight;

		public Node(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}

	private static class Solution {
		private final int N;
		private final int M;
		private final int S;
		private final int D;
		private final int[][] edges;

		public Solution(int N, int M, int S, int D, int[][] edges) {
			this.N = N;
			this.M = M;
			this.S = S;
			this.D = D;
			this.edges = edges;
		}

		public int solve() {
			List<Node>[] graph = getGraph();

			int[] costs = dijkstra(graph, S);

			List<Node>[] reversedGraph = getReversedGraph();
			Set<Integer>[] ignoredEdges = findIgnore(reversedGraph, costs, D);

			List<Node>[] ignoredGraph = ignoreEdge(graph, ignoredEdges);

			costs = dijkstra(ignoredGraph, S);

			return costs[D] == Integer.MAX_VALUE ? -1 : costs[D];
		}

		private List<Node>[] ignoreEdge(List<Node>[] graph, Set<Integer>[] edges) {
			List<Node>[] ignoredGraph = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				ignoredGraph[i] = new ArrayList<>();
			}

			for (int i = 0; i < N; i++) {
				for (Node node : graph[i]) {
					if (!edges[i].contains(node.node)) {
						ignoredGraph[i].add(node);
					}
				}
			}

			return ignoredGraph;
		}

		private Set<Integer>[] findIgnore(List<Node>[] graph, int[] costs, int start) {
			Set<Integer>[] set = new HashSet[N];
			for (int i = 0; i < N; i++) {
				set[i] = new HashSet<>();
			}

			Queue<Node> dq = new ArrayDeque<>();
			boolean[] visited = new boolean[N];
			visited[D] = true;

			dq.add(new Node(start, 0));

			while (!dq.isEmpty()) {
				Node cur = dq.poll();

				for (Node next : graph[cur.node]) {
					if (costs[next.node] + next.weight == costs[cur.node]) {
						set[next.node].add(cur.node);

						if (!visited[next.node]) {
							visited[next.node] = true;
							dq.add(next);
						}
					}
				}
			}

			return set;
		}

		private List<Node>[] getReversedGraph() {
			List<Node>[] graph = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				int[] edge = edges[i];
				int src = edge[0];
				int dst = edge[1];
				int cost = edge[2];

				graph[dst].add(new Node(src, cost));
			}

			return graph;
		}

		private int[] dijkstra(List<Node>[] graph, int start) {
			int[] nodes = new int[N];
			Arrays.fill(nodes, Integer.MAX_VALUE);

			Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.weight));

			nodes[start] = 0;
			pq.add(new Node(start, 0));

			while (!pq.isEmpty()) {
				Node cur = pq.poll();

				if (cur.weight > nodes[cur.node])
					continue;

				for (Node next : graph[cur.node]) {
					int nextWeight = cur.weight + next.weight;
					if (nextWeight < nodes[next.node]) {
						nodes[next.node] = nextWeight;
						pq.add(new Node(next.node, nextWeight));
					}
				}
			}

			return nodes;
		}

		private List<Node>[] getGraph() {
			List<Node>[] graph = new ArrayList[N];
			for (int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				int[] edge = edges[i];
				int src = edge[0];
				int dst = edge[1];
				int cost = edge[2];

				graph[src].add(new Node(dst, cost));
			}

			return graph;
		}

	}
}