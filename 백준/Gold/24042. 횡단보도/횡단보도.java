import java.io.*;
import java.util.*;

public class Main {
	static class Edge {
		int signalIdx;
		int dst;
		public Edge(int signalIdx, int dst) {
			this.signalIdx = signalIdx;
			this.dst = dst;
		}
	}

	static class State implements Comparable<State> {
		int cur;
		long distance;
		public State(int cur, long distance) {
			this.cur = cur;
			this.distance = distance;
		}
		public int compareTo(State s) {
			return Long.compare(this.distance, s.distance);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Edge>[] graph = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			graph[a].add(new Edge(i, b));
			graph[b].add(new Edge(i, a));
		}

		PriorityQueue<State> pq = new PriorityQueue<>();
		long[] visited = new long[N];
		Arrays.fill(visited, Long.MAX_VALUE);
		visited[0] = 0;

		pq.add(new State(0, 0));

		while (!pq.isEmpty()) {
			State state = pq.poll();
			int cur = state.cur;
			long dist = state.distance;

			if (visited[cur] < dist) continue;

			for (Edge e : graph[cur]) {
				int next = e.dst;
				int signalIdx = e.signalIdx;
				long nextCost;
				if (dist <= signalIdx) {
					nextCost = signalIdx + 1;
				} else {
					long times = (dist - signalIdx + M - 1) / M;
					nextCost = times * M + signalIdx + 1;
				}
				if (visited[next] > nextCost) {
					visited[next] = nextCost;
					pq.add(new State(next, nextCost));
				}
			}
		}
		System.out.println(visited[N - 1]);
	}
}
