import java.io.*;
import java.util.*;

class Solution {
	static int N;
	static int[][] grid;

	static int answer;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			grid = new int[N][N];
			answer = -1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			solve();

			sb.append(String.format("#%d %d\n", t, answer));
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static int sRow, sCol;
	static boolean[] visited;

	static void solve() {
		visited = new boolean[101];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sRow = i;
				sCol = j;
				dfs(i, j, 0, 0);
			}
		}
	}

	static int[][] deltas = { { 1, 1 }, { 1, -1 }, { -1, -1 }, { -1, 1 } };

	static void dfs(int r, int c, int stk, int d) {
		if (r == sRow && c == sCol && stk > 1) {
			answer = Math.max(answer, stk);
			return;
		}

		for (int nd = d; nd <= d + 1; nd++) {
			if (nd > 3)
				break;

			int nr = r + deltas[nd][0];
			int nc = c + deltas[nd][1];
			if (isValid(nr, nc) && !visited[grid[nr][nc]]) {
				visited[grid[nr][nc]] = true;
				dfs(nr, nc, stk + 1, nd);
				visited[grid[nr][nc]] = false;
			}
		}
	}

	static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}