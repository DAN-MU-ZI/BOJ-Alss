import java.io.*;
import java.util.*;

public class Main {
	static int n, m, k;
	static int[] city;
	static List<Edge>[] graph;
	static Queue<Integer>[] pCosts;

	static {
		int CITY_SIZE = 1000;

		city = new int[CITY_SIZE + 1];

		graph = new ArrayList[CITY_SIZE + 1];
		for (int i = 1; i <= CITY_SIZE; i++) {
			graph[i] = new ArrayList<>();
		}

		pCosts = new PriorityQueue[CITY_SIZE + 1];
		for (int i = 1; i <= CITY_SIZE; i++) {
			pCosts[i] = new PriorityQueue<>((s1, s2) -> s2 - s1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		init();

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Edge(b, c));
		}

		int[] costs = solve();
		for (int i = 1; i <= n; i++) {
			sb.append(costs[i] + "\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	private static int[] solve() {
		return dijkstra(1);
	}

	private static void init() {
		for (int i = 1; i <= n; i++) {
			graph[i].clear();
		}
		for (int i = 1; i <= n; i++) {
			pCosts[i].clear();
		}
	}
	private static int[] dijkstra(int s) {
		Queue<State> pq = new PriorityQueue<>();

		pCosts[s].add(0);
		pq.add(new State(s, 0));

		while (!pq.isEmpty()) {
			State cur = pq.poll();
			int node = cur.node;
			int cost = cur.cost;

			for (Edge next : graph[node]) {
				int nextNode = next.v;
				int nextCost = cost + next.w;

				if (pCosts[nextNode].isEmpty() || pCosts[nextNode].size() < k) {
					pq.add(new State(nextNode, nextCost));
					pCosts[nextNode].add(nextCost);
				} else if (nextCost <= pCosts[nextNode].peek()) {
					pq.add(new State(nextNode, nextCost));
					pCosts[nextNode].poll();
					pCosts[nextNode].add(nextCost);
				}
			}
		}

		int[] costs = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			if (pCosts[i].size() < k) {
				costs[i] = -1;
			} else {
				costs[i] = pCosts[i].peek();
			}
		}
		return costs;
	}

	static class State implements Comparable<State> {
		int node, cost;

		public State(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(State o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	static class Edge {
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
