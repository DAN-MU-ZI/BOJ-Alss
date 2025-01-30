import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N, M, K;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[] directions = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			directions[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		int[][] priorities = new int[M * 4][4];
		for (int i = 0; i < M * 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				priorities[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Ocean solution = new Ocean(N, M, K, map, directions, priorities);
		sb.append(solution.solve()).append("\n");

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Ocean {
		class Shark {
			int r, c, direction, num;
			int[][] priority;

			Shark(int r, int c, int direction, int[][] priority, int num) {
				this.r = r;
				this.c = c;
				this.direction = direction;
				this.priority = priority;
				this.num = num;
			}
		}

		class Smell {
			Shark shark;
			int remain;

			Smell(Shark shark, int remain) {
				this.shark = shark;
				this.remain = remain;
			}
		}

		int N, M, K;
		Shark[][] map;
		Smell[][] smell;
		Shark[] sharks;

		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		public Ocean(int N, int M, int K, int[][] map, int[] directions, int[][] priorities) {
			// 맵 초기화
			this.N = N;
			this.M = M;
			this.K = K;
			this.map = new Shark[N][N];
			this.smell = new Smell[N][N];
			this.sharks = new Shark[M];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int num = map[i][j] - 1;
					if (num > -1) {
						int[][] priority = new int[4][4];
						for (int k = 0; k < 4; k++) {
							for (int l = 0; l < 4; l++) {
								priority[k][l] = priorities[num * 4 + k][l] - 1;
							}
						}

						Shark shark = new Shark(i, j, directions[num], priority, num);
						sharks[num] = shark;
						this.map[i][j] = shark;
						this.smell[i][j] = new Smell(shark, K);
					}
				}
			}
		}

		public int solve() {
			int turn = 0;
			// while 문 turn 진행
			while (isAnotherShark()) {
				turn++;
				if (turn > 1000)
					return -1;

				// 이동 목록을 가져옴
				Map<Integer, int[]> nextPosition = getNextPositions();
				for (int num : nextPosition.keySet()) {
					int[] pos = nextPosition.get(num);
					int r = pos[0];
					int c = pos[1];
					int d = pos[2];
					Shark shark = sharks[num];
					map[shark.r][shark.c] = null;
					if (map[r][c] == null || map[r][c].num > num) {
						if (map[r][c] != null && map[r][c].num > num) {
							Shark remove = map[r][c];
							sharks[remove.num] = null;
						}
						map[r][c] = shark;
						shark.r = r;
						shark.c = c;
						shark.direction = d;
					} else {
						sharks[num] = null;
					}
				}

				// smell 카운트 줄이기
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						Smell s = smell[i][j];
						if (s != null) {
							s.remain--;
							if (s.remain == 0) {
								smell[i][j] = null;
							}
						}
					}
				}

				for (int num = 0; num < M; num++) {
					Shark shark = sharks[num];
					if (shark == null)
						continue;
					int r = shark.r;
					int c = shark.c;
					smell[r][c] = new Smell(shark, K);
				}

			}

			return turn;
		}

		private Map<Integer, int[]> getNextPositions() {
			Map<Integer, int[]> nextPosition = new HashMap<>();
			for (int num = 0; num < M; num++) {
				Shark shark = sharks[num];
				if (shark == null)
					continue;

				for (int nd : shark.priority[shark.direction]) {
					int nr = shark.r + dr[nd];
					int nc = shark.c + dc[nd];

					if (isValid(nr, nc) && smell[nr][nc] == null) {
						nextPosition.put(num, new int[] {nr, nc, nd});
						break;
					}
				}

				if (!nextPosition.containsKey(num)) {
					for (int nd : shark.priority[shark.direction]) {
						int nr = shark.r + dr[nd];
						int nc = shark.c + dc[nd];

						if (isValid(nr, nc) && smell[nr][nc].shark.num == num) {
							nextPosition.put(num, new int[] {nr, nc, nd});
							break;
						}
					}
				}
			}
			return nextPosition;
		}

		private boolean isValid(int r, int c) {
			return 0 <= r && r < N && 0 <= c && c < N;
		}

		private boolean isAnotherShark() {
			for (int i = 1; i < M; i++) {
				if (sharks[i] != null)
					return true;
			}
			return false;
		}
	}
}