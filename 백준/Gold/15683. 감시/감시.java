import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] grid;
	static int[][][][] deltas = {
		{},
		{{{1, 0}}, {{-1, 0}}, {{0, 1}}, {{0, -1}}},
		{{{1, 0}, {-1, 0}}, {{0, 1}, {0, -1}}},
		{{{1, 0}, {0, -1}}, {{1, 0}, {0, 1}}, {{-1, 0}, {0, 1}}, {{-1, 0}, {0, -1}}},
		{{{-1, 0}, {0, 1}, {0, -1}}, {{1, 0}, {0, 1}, {0, -1}}, {{1, 0}, {-1, 0}, {0, -1}}, {{1, 0}, {-1, 0}, {0, 1}}},
		{{{1, 0}, {-1, 0}, {0, -1}, {0, 1}}}
	};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		grid = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = solve();
		bw.write(String.valueOf(answer));

		bw.close();
		br.close();
	}

	static class Camera {
		int r, c, t;

		public Camera(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}

	static Camera[] cameras;
	static int C;

	public static int solve() {
		/**
		 * 1. 카메라 찾기 : List<Camera>
		 * 2. 방향 조합 만들기 : List<int[]>
		 * 3. 감시하기
		 * 3-1. 백업해둔 그리드 복사
		 * 3-2. 카메라 별로 감시
		 * 4. 계산하기
		 */
		listingCameras();
		generateCombinations();
		simulate();

		return answer;
	}

	static int answer;
	static boolean[][] watch;

	private static void simulate() {
		answer = Integer.MAX_VALUE;

		for (int[] comb : combs) {
			watch = new boolean[N][M];

			for (int i = 0; i < C; i++) {
				Camera camera = cameras[i];
				int[][] delta = deltas[camera.t][comb[i]];
				watch(camera, delta);
			}

			// for (boolean[] line : watch) {
			// 	System.out.println(Arrays.toString(line));
			// }
			// System.out.println();

			int sum = summation();
			answer = Math.min(answer, sum);
		}
	}

	private static int summation() {
		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (grid[r][c] == 0 && !watch[r][c]) {
					count++;
				}
			}
		}
		return count;
	}

	private static void watch(Camera camera, int[][] delta) {
		watch[camera.r][camera.c] = true;
		for (int[] d : delta) {
			int nr = camera.r;
			int nc = camera.c;

			while (true) {
				nr += d[0];
				nc += d[1];

				if (isValid(nr, nc) && grid[nr][nc] < 6) {
					watch[nr][nc] = true;
				} else {
					break;
				}
			}
		}
	}

	private static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}

	static List<int[]> combs;
	static int[] comb;

	static void generateCombinations() {
		combs = new ArrayList<>();
		comb = new int[C];
		dfs(0);
		// for (int[] c: combs) {
		// 	System.out.println(Arrays.toString(c));
		// }
	}

	static void dfs(int idx) {
		if (idx == C) {
			combs.add(comb.clone());
			return;
		}

		Camera camera = cameras[idx];
		for (int i = 0; i < deltas[camera.t].length; i++) {
			comb[idx] = i;
			dfs(idx + 1);
		}
	}

	static void listingCameras() {
		cameras = new Camera[8];
		C = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				int cell = grid[r][c];
				if (1 <= cell && cell <= 5) {
					cameras[C++] = new Camera(r, c, cell);
				}
			}
		}

		// System.out.println(Arrays.toString(cameras));
	}
}