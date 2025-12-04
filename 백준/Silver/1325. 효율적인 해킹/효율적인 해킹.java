import java.io.*;
import java.util.*;

public class Main {

	static List<Integer>[] graph;
	static int[] visited;
	static int visitToken = 1;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		buildGraph(br);

		int[] reachCount = new int[N + 1];
		int maxReach = 0;

		for (int node = 1; node <= N; node++) {
			int count = bfsCount(node);
			reachCount[node] = count;
			maxReach = Math.max(maxReach, count);
		}

		printResult(reachCount, maxReach);
	}

	private static void buildGraph(BufferedReader br) throws Exception {
		graph = new ArrayList[N + 1];
		visited = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[b].add(a);  // b를 해킹하면 a도 해킹 가능
		}
	}

	private static int bfsCount(int start) {
		ArrayDeque<Integer> queue = new ArrayDeque<>();
		int token = visitToken++;

		visited[start] = token;
		queue.add(start);

		int count = 1;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int next : graph[current]) {
				if (visited[next] == token) continue;
				visited[next] = token;
				queue.add(next);
				count++;
			}
		}

		return count;
	}

	private static void printResult(int[] reachCount, int maxReach) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (reachCount[i] == maxReach) {
				sb.append(i).append(' ');
			}
		}
		System.out.print(sb);
	}
}
