import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<Integer>[] graph;
	static int N, M;
	static int[] visit;
	static int visitId = 0;
	static int[] queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph[B].add(A);
		}

		visit = new int[N + 1];
		queue = new int[N + 1];

		int[] count = new int[N + 1];
		int max = 0;

		for (int start = 1; start <= N; start++) {
			int hacked = bfs(start);
			count[start] = hacked;
			if (hacked > max) {
				max = hacked;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (count[i] == max) {
				sb.append(i).append(' ');
			}
		}

		System.out.print(sb);
	}

	static int bfs(int start) {
		visitId++;
		int front = 0, rear = 0;

		visit[start] = visitId;
		queue[rear++] = start;

		int cnt = 1;

		while (front < rear) {
			int cur = queue[front++];

			for (int next : graph[cur]) {
				if (visit[next] == visitId) continue;
				visit[next] = visitId;
				queue[rear++] = next;
				cnt++;
			}
		}

		return cnt;
	}
}
