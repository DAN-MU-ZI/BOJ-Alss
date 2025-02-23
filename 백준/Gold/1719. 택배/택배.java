import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		graph = new int[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a][b] = c;
			graph[b][a] = c;
		}

		int[][] answer = solve();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (answer[i][j] == 0) {
					sb.append('-');
				} else {
					sb.append(answer[i][j]);
				}
				sb.append(" ");
			}
			sb.append("\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static int[][] solve() {
		int[][] answer = new int[n + 1][n + 1];

		for (int start = 1; start <= n; start++) {
			int[] firstVisit = dijkstra(start);

			for (int end = 1; end <= n; end++) {
				if (start == end) {
					answer[start][end] = 0;
				} else {
					answer[start][end] = firstVisit[end];
				}
			}
		}
		return answer;
	}

	private static int[] dijkstra(int s) {
		int[] firstVisit = new int[n + 1];
		Arrays.fill(firstVisit, -1);
		int[] costs = new int[n + 1];
		Arrays.fill(costs, Integer.MAX_VALUE);

		Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
		costs[s] = 0;
		pq.add(new int[] {s, 0});

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			int cost = cur[1];

			if (cost > costs[node]) continue;

			for (int nextNode = 1; nextNode <= n; nextNode++) {
				if (graph[node][nextNode] == 0) continue;

				int nextCost = cost + graph[node][nextNode];
				if (nextCost < costs[nextNode]) {
					costs[nextNode] = nextCost;
					pq.add(new int[] {nextNode, nextCost});

					firstVisit[nextNode] = (node == s) ? nextNode : firstVisit[node];
				}
			}
		}
		return firstVisit;
	}

}
