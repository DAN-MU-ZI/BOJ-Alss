import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		Solution solution = new Solution(N, M, map);
		sb.append(solution.solve()).append("\n");

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private final int N;
		private final int M;
		private final int[][] map;
		private final boolean[][] visited;
		private final int[] dr = {1, 0, -1, 0};
		private final int[] dc = {0, 1, 0, -1};
		private int answer = 0;

		public Solution(int N, int M, int[][] map) {
			this.N = N;
			this.M = M;
			this.map = map;
			visited = new boolean[N][M];
		}

		public int solve() {
			dfs(0);
			return answer;
		}

		public void dfs(int built) {
			if (built == 3) {
				int safe = 0;

				boolean[][] visited = new boolean[N][M];
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < M; c++) {
						if (!visited[r][c] && map[r][c] == 0) {
							safe += bfs(visited, r, c);
						}
					}
				}

				answer = Math.max(answer, safe);
				return;
			}

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if (!visited[r][c] && map[r][c] == 0) {
						visited[r][c] = true;
						map[r][c] = 1;
						dfs(built + 1);
						map[r][c] = 0;
						visited[r][c] = false;
					}
				}
			}
		}

		public int bfs(boolean[][] visited, int x, int y) {
			int cnt = 0;

			Queue<int[]> q = new ArrayDeque<>();

			q.add(new int[] {x, y});
			visited[x][y] = true;
			cnt++;

			boolean isVirus = false;
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];

				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];

					if (!isValid(nr, nc))
						continue;
					if (map[nr][nc] == 2) {
						isVirus = true;
					}
					if (map[nr][nc] == 1 || visited[nr][nc])
						continue;

					visited[nr][nc] = true;
					q.add(new int[] {nr, nc});
					cnt++;
				}
			}

			if (isVirus) return 0;
			return cnt;
		}

		public boolean isValid(int r, int c) {
			return 0 <= r && r < N && 0 <= c && c < M;
		}
	}
}