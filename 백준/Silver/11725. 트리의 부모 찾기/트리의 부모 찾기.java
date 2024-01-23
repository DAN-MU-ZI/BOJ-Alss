import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer>[] graph = new ArrayList[N + 1];
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

		int[] visited = new int[N + 1];
		Stack<Integer> stk = new Stack<>();
		stk.push(1);
		visited[1] = 1;
		while (!stk.isEmpty()) {
			int num = stk.pop();
			for (int i : graph[num]) {
				if (visited[i] == 0) {
					visited[i] = num;
					stk.push(i);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < N + 1; i++) {
			sb
				.append(visited[i])
				.append("\n");
		}
		System.out.println(sb);
	}
}