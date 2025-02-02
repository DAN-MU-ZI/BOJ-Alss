import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N, Q;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		int size = (int)Math.pow(2, N);
		int[][] iceGrid = new int[size][size];
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				iceGrid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] spellLevels = new int[Q];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			spellLevels[i] = Integer.parseInt(st.nextToken());
		}

		GlacierMap solution = new GlacierMap(N, Q, iceGrid, spellLevels);
		int[] answer = solution.solve();
		for (int ans : answer) {
			sb.append(ans).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class GlacierMap {
		int[] dr = {1, 0, -1, 0};
		int[] dc = {0, 1, 0, -1};

		int N, Q, size;
		int[] spellLevels;
		int[][] iceGrid;

		public GlacierMap(int N, int Q, int[][] iceGrid, int[] spellLevels) {
			this.N = N;
			this.Q = Q;
			this.size = (int)Math.pow(2, N);
			this.spellLevels = spellLevels;
			this.iceGrid = iceGrid;
		}

		public int[] solve() {
			/**
			 * 1. 격자 나누기
			 * 2. 돌리기
			 * 3. 얼음 녹이기
			 */
			for (int spellLevel : spellLevels) {
				// System.out.println("spellLevel = " + spellLevel);

				casting(spellLevel);

				// for (int[] line : iceGrid) {
				// 	System.out.println(Arrays.toString(line));
				// }
				// System.out.println();

				meltIce();

				// for (int[] line : iceGrid) {
				// 	System.out.println(Arrays.toString(line));
				// }
				// System.out.println();
			}

			return summation();
		}

		private int[] summation() {
			int sum = 0;
			int biggest = 0;
			boolean[][] visited = new boolean[size][size];

			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					if (iceGrid[r][c] > 0 && !visited[r][c]) {
						int[] info = bfs(visited, r, c); // info[0] = sum, info[1] = size
						sum += info[0];
						biggest = Math.max(biggest, info[1]);
					}
				}
			}
			return new int[] {sum, biggest};
		}

		private int[] bfs(boolean[][] visited, int r, int c) {
			int sum = 0;
			int size = 0;
			Queue<int[]> q = new ArrayDeque<>();

			q.add(new int[] {r, c});
			visited[r][c] = true;
			sum += iceGrid[r][c];
			size++;

			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0];
				int y = cur[1];

				for (int i = 0; i < 4; i++) {
					int nr = x + dr[i];
					int nc = y + dc[i];
					if (isValid(nr, nc) && iceGrid[nr][nc] > 0 && !visited[nr][nc]) {
						size++;
						sum += iceGrid[nr][nc];
						q.add(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
			}

			return new int[] {sum, size};
		}

		private void meltIce() {
			int[][] newIceGrids = new int[size][size];

			for (int r = 0; r < size; r++) {
				for (int c = 0; c < size; c++) {
					int isIceCount = 0;
					for (int k = 0; k < 4; k++) {
						int nr = r + dr[k];
						int nc = c + dc[k];
						if (isValid(nr, nc) && iceGrid[nr][nc] > 0) {
							isIceCount++;
						}
					}
					if (isIceCount >= 3) {
						newIceGrids[r][c] = iceGrid[r][c];
					} else {
						newIceGrids[r][c] = iceGrid[r][c] - 1;
					}
				}
			}

			iceGrid = newIceGrids;
		}

		private void casting(int spellLevel) {
			int scale = (int)Math.pow(2, spellLevel);
			for (int i = 0; i * scale < size; i++) {
				for (int j = 0; j * scale < size; j++) {
					rotation(i * scale, j * scale, scale);
				}
			}
		}

		private void rotation(int r, int c, int scale) {
			int[][] section = new int[scale][scale];
			for (int i = 0; i < scale; i++) {
				for (int j = 0; j < scale; j++) {
					section[i][j] = iceGrid[r + scale - 1 - j][c + i];
				}
			}
			for (int i = 0; i < scale; i++) {
				for (int j = 0; j < scale; j++) {
					iceGrid[r + i][c + j] = section[i][j];
				}
			}
		}

		private boolean isValid(int r, int c) {
			return 0 <= r && r < size && 0 <= c && c < size;
		}

	}
}