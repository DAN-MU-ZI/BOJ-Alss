import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[][] grid;
	static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			grid = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[] answer = solve();
			sb.append(String.format("#%d %d %d\n", t, answer[0], answer[1]));
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static int[][] move;
	static int[] solve() {
		move = new int[N][N];

		int[] answer = new int[2];
		answer[0] = Integer.MAX_VALUE;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (move[r][c] == 0) {
					dfs(r, c);
				}
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (move[r][c] > answer[1]) {
					answer[0] = grid[r][c];
					answer[1] = move[r][c];
				} else if(move[r][c] == answer[1] && grid[r][c] < answer[0]) {
					answer[0] = grid[r][c];
				}
			}
		}
		return answer;
	}

	static void dfs(int r, int c) {
		move[r][c] = 1;

		for (int[] delta: deltas) {
			int nr = r + delta[0];
			int nc = c + delta[1];

			if (isValid(nr, nc) && grid[r][c] + 1 == grid[nr][nc]) {
				dfs(nr, nc);
				move[r][c] = move[nr][nc] + 1;
			}
		}
	}

	static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}