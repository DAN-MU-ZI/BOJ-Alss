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
		int[][] edges = new int[E][3];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			edges[i] = new int[] {A, B, C};
		}
		Solution solution = new Solution(V, E, edges);
		sb.append(solution.solve());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private final int V;
		private final int E;
		private final int[][] edges;

		public Solution(int V, int E, int[][] edges) {
			this.V = V;
			this.E = E;
			this.edges = edges;
		}

		public int solve() {
			List<int[]>[] graph = new ArrayList[V + 1];
			for (int i = 0; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int[] input : edges) {
				graph[input[0]].add(new int[] {input[1], input[2]});
				graph[input[1]].add(new int[] {input[0], input[2]});
			}

			Queue<int[]> pq = new PriorityQueue<>((s1, s2) -> s1[1] - s2[1]);
			boolean[] visited = new boolean[V + 1];

			int start = 1;
			pq.add(new int[] {start, 0});

			int answer = 0;
			while (!pq.isEmpty()) {
				int[] cur = pq.poll();

				int node = cur[0];
				int weight = cur[1];

				if (visited[node])
					continue;

				visited[node] = true;
				answer += weight;

				for (int[] next : graph[node]) {
					int nextNode = next[0];
					int nextWeight = next[1];

					if (visited[nextNode])
						continue;
					pq.add(new int[] {nextNode, nextWeight});
				}
			}

			return answer;
		}
	}
}