import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[] size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		size = new int[N + 1];
		graph = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		countSubTree(R);

		StringBuilder answer = new StringBuilder();
		while (Q-- > 0) {
			int query = Integer.parseInt(br.readLine());
			answer
				.append(size[query])
				.append("\n");
		}
		System.out.println(answer);
	}

	static int countSubTree(int start) {
		if (size[start] != 0)
			return size[start];

		visited[start] = true;
		int result = 1;
		for (int node : graph[start]) {
			if (!visited[node]) {
				result += countSubTree(node);
			}
		}
		return size[start] = result;
	}
}