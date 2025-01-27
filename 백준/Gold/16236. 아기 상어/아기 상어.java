import java.beans.Visibility;
import java.io.*;
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
		Solution solution = new Solution(N, map);
		sb.append(solution.solve());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private final int N;
		private final int[][] map;
		private int r, c;
		private int eatCount = 0;
		private int size = 2;
		private final int[] dr = {-1, 0, 0, 1};
		private final int[] dc = {0, -1, 1, 0};

		public Solution(int N, int[][] map) {
			this.N = N;
			this.map = map;
		}

		public int solve() {
			init();
			int time = 0;
			while (true) {
				int[] nextFish = find();
				if (nextFish == null) {
					break;
				}
				r = nextFish[0];
				c = nextFish[1];
				time += nextFish[2];

				map[r][c] = 0;
				eatCount++;
				if (eatCount == size) {
					size++;
					eatCount = 0;
				}
			}

			return time;
		}

		private int[] find() {
			PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
				if (a[2] != b[2]) return a[2] - b[2];
				if (a[0] != b[0]) return a[0] - b[0];
				return a[1] - b[1];
			});

			boolean[][] visited = new boolean[N][N];
			Queue<int[]> queue = new ArrayDeque<>();
			queue.add(new int[] {r, c, 0});
			visited[r][c] = true;

			while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				int x = cur[0];
				int y = cur[1];
				int distance = cur[2];

				if (1 <= map[x][y] && map[x][y] < size) {
					pq.add(new int[] {x, y, distance});
				}

				for (int i = 0; i < 4; i++) {
					int nr = x + dr[i];
					int nc = y + dc[i];

					if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] <= size) {
						visited[nr][nc] = true;
						queue.add(new int[] {nr, nc, distance + 1});
					}
				}
			}

			return pq.isEmpty() ? null : pq.poll();
		}

		private boolean isValid(int nr, int nc) {
			return 0 <= nr && nr < N && 0 <= nc && nc < N;
		}

		private void init() {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 9) {
						r = i;
						c = j;
						map[i][j] = 0;
					}
				}
			}
		}
	}
}