import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] inputs = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			inputs[i] = new int[] {a, b};
		}
		Solution solution = new Solution(N, M, inputs);
		int[] results = solution.solve();
		for (int result : results) {
			sb.append(result).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private final int N;
		private final int M;
		private final int[][] arr;

		public Solution(int n, int m, int[][] arr) {
			N = n;
			M = m;
			this.arr = arr;
		}

		public int[] solve() {
			boolean[][] isHeavier = new boolean[N + 1][N + 1];
			for (int[] input : arr) {
				int a = input[0];
				int b = input[1];
				isHeavier[a][b] = true;
			}

			int[] answer = new int[N];
			for (int i = 1; i <= N; i++) {
				int forward = bfs(isHeavier, i);
				int backward = reverseBfs(isHeavier, i);
				answer[i - 1] = N - (forward + backward - 1);
			}

			return answer;
		}

		private int bfs(boolean[][] isHeavier, int i) {
			boolean[] visited = new boolean[N];
			Queue<Integer> q = new ArrayDeque<>();
			q.add(i);
			visited[i - 1] = true;

			while (!q.isEmpty()) {
				int cur = q.poll();

				for (int j = 1; j <= N; j++) {
					if (visited[j - 1])
						continue;
					if (!isHeavier[cur][j])
						continue;
					visited[j - 1] = true;
					q.add(j);
				}
			}
			int cnt = 0;
			for(boolean visit: visited) {
				if(visit) cnt++;
			}
			return cnt;
		}

		private int reverseBfs(boolean[][] isHeavier, int i) {
			boolean[] visited = new boolean[N];
			Queue<Integer> q = new ArrayDeque<>();
			q.add(i);
			visited[i - 1] = true;

			while (!q.isEmpty()) {
				int cur = q.poll();

				for (int j = 1; j <= N; j++) {
					if (visited[j - 1])
						continue;
					if (!isHeavier[j][cur])
						continue;
					visited[j - 1] = true;
					q.add(j);
				}
			}
			int cnt = 0;
			for(boolean visit: visited) {
				if(visit) cnt++;
			}
			return cnt;
		}
	}
}