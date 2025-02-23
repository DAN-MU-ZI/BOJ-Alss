import java.io.*;
import java.util.*;

public class Main {
	static final int MAP_SIZE = 100;
	static int W, H;
	static char[][] grid = new char[MAP_SIZE][MAP_SIZE];
	static boolean isFirstC;
	static int[] c1, c2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		isFirstC = false;

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		for (int i = 0; i < H; i++) {
			char[] line = br.readLine().trim().toCharArray();
			for (int j = 0; j < W; j++) {
				grid[i][j] = line[j];

				if (line[j] == 'C') {
					if (isFirstC) {
						c2 = new int[] {i, j};
					} else {
						c1 = new int[] {i, j};
						isFirstC = true;
					}
				}
			}
		}

		bw.write(String.valueOf(solve()));
		bw.close();
		br.close();
	}

	static int solve() {
		return bfs(c1);
	}

	private static int bfs(int[] s) {
		int[][][] visited = new int[H][W][4];
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				Arrays.fill(visited[r][c], Integer.MAX_VALUE);
			}
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>((s1, s2) -> s1[2] - s2[2]);

		int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

		for (int d = 0; d < 4; d++) {
			int nr = c1[0] + deltas[d][0];
			int nc = c1[1] + deltas[d][1];
			if (isValid(nr, nc) && grid[nr][nc] != '*') {
				pq.add(new int[]{nr, nc, 0, d});
				visited[nr][nc][d] = 0;
			}
		}

		int answer = Integer.MAX_VALUE;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int r = cur[0];
			int c = cur[1];
			int stk = cur[2];
			int d = cur[3];

			if (r == c2[0] && c == c2[1]) {
				return stk;
			}

			for (int nd = 0; nd < 4; nd++) {
				int nr = r + deltas[nd][0];
				int nc = c + deltas[nd][1];
				int nStk = d != nd ? stk + 1 : stk;

				if (isValid(nr, nc) && grid[nr][nc] != '*' && nStk < visited[nr][nc][nd]) {
					pq.add(new int[] {nr, nc, nStk, nd});
					visited[nr][nc][nd] = nStk;
				}
			}
		}
		return answer;
	}

	static boolean isValid(int r, int c) {
		return 0 <= r && r < H && 0 <= c && c < W;
	}
}
