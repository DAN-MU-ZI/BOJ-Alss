import java.io.*;
import java.util.*;

public class Solution {

	static int N;
	static String S;
	static int[][] grid;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = st.nextToken().trim();

			grid = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			solve();

			sb.append(String.format("#%d\n", t));
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(grid[i][j] + " ");
				}
				sb.append("\n");
			}
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static void solve() {
		int[][] answer = new int[N][N];

		if (S.equals("up")) {
			for (int j = 0; j < N; j++) {
				int stk = 0;
				for (int i = 0; i < N; i++) {
					if (grid[i][j] == 0)
						continue;

					int tmp = grid[i][j];
					for (int k = i + 1; k < N; k++) {
						if (grid[k][j] == 0)
							continue;
						if (grid[i][j] == grid[k][j]) {
							tmp += grid[i][j];
							grid[i][j] = 0;
							grid[k][j] = 0;
						} else {
							grid[i][j] = 0;
						}

						break;
					}

					answer[stk++][j] = tmp;
				}
			}
		} else if (S.equals("down")) {
			for (int j = 0; j < N; j++) {
				int stk = N - 1;
				for (int i = N - 1; i >= 0; i--) {
					if (grid[i][j] == 0)
						continue;

					int tmp = grid[i][j];
					for (int k = i - 1; k >= 0; k--) {
						if (grid[k][j] == 0)
							continue;

						if (grid[i][j] == grid[k][j]) {
							tmp += grid[i][j];
							grid[i][j] = 0;
							grid[k][j] = 0;
						} else {
							grid[i][j] = 0;
						}

						break;
					}

					answer[stk--][j] = tmp;
				}
			}
		} else if (S.equals("left")) {
			for (int i = 0; i < N; i++) {
				int stk = 0;
				for (int j = 0; j < N; j++) {
					if (grid[i][j] == 0)
						continue;

					int tmp = grid[i][j];
					for (int k = j + 1; k < N; k++) {
						if (grid[i][k] == 0)
							continue;
						if (grid[i][j] == grid[i][k]) {
							tmp += grid[i][j];
							grid[i][j] = 0;
							grid[i][k] = 0;
						} else {
							grid[i][j] = 0;
						}

						break;
					}

					answer[i][stk++] = tmp;
				}
			}
		} else if(S.equals("right")) {
			for (int i = 0; i < N; i++) {
				int stk = N - 1;
				for (int j = N - 1; j >=0; j--) {
					if (grid[i][j] == 0)
						continue;

					int tmp = grid[i][j];
					for (int k = j - 1; k >= 0; k--) {
						if (grid[i][k] == 0)
							continue;

						if (grid[i][j] == grid[i][k]) {
							tmp += grid[i][j];
							grid[i][j] = 0;
							grid[i][k] = 0;
						} else {
							grid[i][j] = 0;
						}

						break;
					}

					answer[i][stk--] = tmp;
				}
			}
		}

		grid = answer;
	}

}