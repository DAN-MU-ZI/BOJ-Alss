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
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		List<Node>[] graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			graph[A].add(new Node(B, 1));
		}
		Solution solution = new Solution(N, M, K, X, graph);
		for(int node: solution.solve()) {
			sb.append(node).append("\n");
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
		private final int K;
		private final int X;
		private final List<Node>[] graph;

		public Solution(int N, int M, int K, int X, List<Node>[] graph) {
			this.N = N;
			this.M = M;
			this.K = K;
			this.X = X;
			this.graph = graph;
		}

		public List<Integer> solve() {
			int[] nodes = dijkstra(this.graph, X);

			List<Integer> answer = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				if (nodes[i] == K)
					answer.add(i);
			}

			if (answer.isEmpty())
				answer.add(-1);

			return answer;
		}

		private int[] dijkstra(List<Node>[] graph, int start) {
			Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.weight));
			int[] nodes = new int[N + 1];
			boolean[] visited = new boolean[N + 1];

			Arrays.fill(nodes, Integer.MAX_VALUE);
			nodes[start] = 0;
			pq.add(new Node(start, 0));
			while (!pq.isEmpty()) {
				Node current = pq.poll();

				if (visited[current.node])
					continue;
				visited[current.node] = true;

				for (Node next : graph[current.node]) {

					if (!visited[next.node] && current.weight + next.weight < nodes[next.node]) {
						nodes[next.node] = current.weight + next.weight;
						pq.add(new Node(next.node, current.weight + next.weight));
					}
				}
			}

			return nodes;
		}
	}
}