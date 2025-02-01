import java.io.*;
import java.nio.file.AccessMode;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Ocean solution = new Ocean(N, map);
		sb.append(solution.solve()).append("\n");

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Ocean {
		int[] dr = {0, 1, 0, -1};
		int[] dc = {-1, 0, 1, 0};
		int[][][] spreadPatterns = {
			{
				{-1, 1, 1}, {1, 1, 1},
				{-2, 0, 2}, {2, 0, 2},
				{-1, 0, 7}, {1, 0, 7},
				{-1, -1, 10}, {1, -1, 10},
				{0, -2, 5}
			},
			{
				{-1, -1, 1}, {-1, 1, 1},
				{0, -2, 2}, {0, 2, 2},
				{0, -1, 7}, {0, 1, 7},
				{1, -1, 10}, {1, 1, 10},
				{2, 0, 5}
			},
			{
				{1, -1, 1}, {-1, -1, 1},
				{2, 0, 2}, {-2, 0, 2},
				{1, 0, 7}, {-1, 0, 7},
				{1, 1, 10}, {-1, 1, 10},
				{0, 2, 5}
			},
			{
				{1, -1, 1}, {1, 1, 1},
				{0, -2, 2}, {0, 2, 2},
				{0, 1, 7}, {0, -1, 7},
				{-1, 1, 10}, {-1, -1, 10},
				{-2, 0, 5}
			}
		};

		int N, direction, distance, cnt, r, c, answer;
		int[][] map;

		public Ocean(int N, int[][] map) {
			this.N = N;
			this.map = map;
			direction = 0;
			distance = 1;
			cnt = 0;
			answer = 0;
			r = N / 2;
			c = N / 2;
		}

		public int solve() {
			while (!(r == 0 && c == 0)) {
				move();
				splitSand();
				renew();
			}

			return answer;
		}

		private void splitSand() {
			int sand = map[r][c];
			int remain = map[r][c];

			for (int[] pattern  : spreadPatterns[direction]) {
				int nr = r + pattern[0];
				int nc = c + pattern[1];
				int amount = (int)((long)sand * pattern [2] / 100);

				if (isValid(nr, nc)) {
					map[nr][nc] += amount;
				} else {
					answer += amount;
				}
				remain -= amount;
			}
			int nr = r + dr[direction];
			int nc = c + dc[direction];

			if (isValid(nr, nc)) {
				map[nr][nc] += remain;
			} else {
				answer += remain;
			}

			map[r][c] = 0;
		}

		private boolean isValid(int r, int c) {
			return 0 <= r && r < N && 0 <= c && c < N;
		}

		private void renew() {
			cnt++;

			// 거리 증가가 필요한 경우
			if (cnt == distance * 2) {
				cnt = 0;
				distance++;
			}

			// 방향 전환
			if (cnt % distance == 0) {
				direction = (direction + 1) % 4;
			}
		}


		private void move() {
			r += dr[direction];
			c += dc[direction];
		}
	}
}