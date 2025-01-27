import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		Solution solution;
		while (T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			solution = new Solution(n, arr);
			sb.append(solution.solve()).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private final int n;
		private final int[] arr;
		private final boolean[] visited;
		private final boolean[] isComplete;
		private int answer = 0;

		public Solution(int n, int[] arr) {
			this.n = n;
			this.arr = arr;
			this.visited = new boolean[n + 1];
			this.isComplete = new boolean[n + 1];
		}

		public int solve() {
			for (int i = 1; i <= n; i++) {
				if (isComplete[i])
					continue;
				dfs(i);
			}
			return n - answer;
		}

		private void dfs(int start) {
			if (isComplete[start]) {
				return;
			}
			if (visited[start]) {
				int node = start;
				do {
					node = arr[node];
					isComplete[node] = true;
					answer++;
				} while (node != start);
				return;
			}

			visited[start] = true;
			dfs(arr[start]);
			isComplete[start] = true;
			visited[start] = false;
		}
	}
}