import java.io.*;
import java.util.*;

public class Main {
	static int M, N;
	static int[][] grid;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		grid = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = solve();

		bw.write(String.valueOf(answer));
		bw.close();
		br.close();
	}

	static int solve() {
		int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

		int[][] visited = new int[M][N];

		Queue<int[]> dq = new PriorityQueue<>((s1, s2) -> grid[s2[0]][s2[1]] - grid[s1[0]][s1[1]]);

		dq.add(new int[] {0, 0});
		visited[0][0] = 1;

		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0];
			int c = cur[1];

			if (r == M - 1 && c == N - 1) {
				continue;
			}

			for (int[] delta : deltas) {
				int nr = r + delta[0];
				int nc = c + delta[1];

				if (!isValid(nr, nc) || grid[nr][nc] >= grid[r][c]) {
					continue;
				}

				if (visited[nr][nc] == 0) {
					dq.add(new int[] {nr, nc});
				}

				visited[nr][nc] += visited[r][c];
			}
		}
		return visited[M - 1][N - 1];
	}

	static boolean isValid(int r, int c) {
		return 0 <= r && r < M && 0 <= c && c < N;
	}
}
