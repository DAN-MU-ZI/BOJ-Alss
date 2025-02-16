import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static boolean[][] grid;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			grid = new boolean[100][N];

			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int height = Integer.parseInt(st.nextToken());
				for (int r = 0; r < height; r++) {
					grid[r][c] = true;
				}
			}

			int answer = solve();
			sb.append(String.format("#%d %d\n", t, answer));
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static int solve() {
		int answer = 0;
		for (int r = 0; r < 100; r++) {
			for (int c = N - 1; c >= 0; c--) {
				if (grid[r][c]) {
					int nc = c;
					while (nc < N - 1 && !grid[r][nc + 1]) {
						nc++;
					}
					grid[r][c] = false;
					grid[r][nc] = true;
					answer = Math.max(answer, nc - c);
				}
			}
		}

		return answer;
	}
}