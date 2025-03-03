import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[] arr;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[M];

		solve();

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static boolean[] visited;
	static void solve() {
		visited = new boolean[N + 1];

		dfs(0);
	}

	static void dfs(int idx) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (visited[i]) continue;

			visited[i] = true;
			arr[idx] = i;
			dfs(idx + 1);

			visited[i] = false;
		}
	}
}
