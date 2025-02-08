import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());

		int[][] fishes = new int[M][3];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			fishes[i][0] = Integer.parseInt(st.nextToken()) - 1;
			fishes[i][1] = Integer.parseInt(st.nextToken()) - 1;
			fishes[i][2] = Integer.parseInt(st.nextToken()) - 1;
		}

		int[] shark = new int[2];
		st = new StringTokenizer(br.readLine());
		shark[0] = Integer.parseInt(st.nextToken()) - 1;
		shark[1] = Integer.parseInt(st.nextToken()) - 1;

		Solution solution = new Solution(M, S, fishes, shark);
		sb.append(solution.solve());

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static class Solution {
		int N = 4;
		int M, S;
		List<Integer>[][] map, copy;
		int[][] smell;
		int[][] fDeltas = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
		int[] shark;

		public Solution(int M, int S, int[][] fishes, int[] shark) {
			this.M = M;
			this.S = S;
			map = createMap();
			for (int[] fish : fishes) {
				map[fish[0]][fish[1]].add(fish[2]);
			}
			this.shark = shark;
			smell = new int[N][N];
		}

		public int solve() {
			// printInfo();
			while (S-- > 0) {
				// System.out.println("start");
				// 맵 복사
				copy = createMap();
				copyMap(copy);
				// printInfo();
				// 물고기 한칸 이동
				// System.out.println("물고기 이동");
				moveFishes();
				// printInfo();
				// 상어 이동
				moveShark();
				// printInfo();
				// 냄새 제거(2인거 제거)
				removeSmell();
				// printInfo();
				// 물고기 복붙
				pasteMap();
				// printInfo();
			}
			int answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					answer += map[i][j].size();
				}
			}
			return answer;
		}

		private void printInfo() {
			System.out.printf("shark = [%d, %d]\n", shark[0] + 1, shark[1] + 1);
			System.out.println(Arrays.deepToString(smell));
			for (int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(map[i]));
			}
			System.out.println();
		}

		private void pasteMap() {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j].addAll(copy[i][j]);
				}
			}
		}

		private void removeSmell() {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (smell[i][j] > 0) {
						smell[i][j]--;
					}
				}
			}
		}

		int[][] sDeltas = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
		int[] find, fixed;
		int best;

		private void moveShark() {
			find = new int[3];
			fixed = new int[3];
			best = -1;
			dfs(0);
			int nr = shark[0];
			int nc = shark[1];
			// if (!map[nr][nc].isEmpty()) {
			// 	map[nr][nc].clear();
			// 	smell[nr][nc] = 3;
			// }

			for (int d : fixed) {
				nr += sDeltas[d][0];
				nc += sDeltas[d][1];
				if (!map[nr][nc].isEmpty()) {
					map[nr][nc].clear();
					smell[nr][nc] = 3;
				}
			}
			// System.out.println(Arrays.toString(fixed));
			// System.out.println(nr + ":" + nc);
			shark = new int[] {nr, nc};
		}

		private void dfs(int stk) {
			if (stk == 3) {
				boolean[][] visited = new boolean[N][N];
				int nr = shark[0];
				int nc = shark[1];
				int score = map[nr][nc].size();

				for (int d : find) {
					nr += sDeltas[d][0];
					nc += sDeltas[d][1];
					if (!isValid(nr, nc) )
						return;

					if(!visited[nr][nc])
						score += map[nr][nc].size();
					visited[nr][nc] = true;
				}

				if (best < score) {
					for (int i = 0; i < 3; i++) {
						fixed[i] = find[i];
					}
					best = score;
					// System.out.println(Arrays.toString(fixed) + ", " + score);
					return;
				}
				return;
			}

			for (int i = 0; i < 4; i++) {
				find[stk] = i;
				dfs(stk + 1);
			}
		}

		private void moveFishes() {
			List<Integer>[][] newMap = createMap();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int d : map[i][j]) {
						moveFish(d, i, j, newMap);
					}
				}
			}
			map = newMap;
		}

		private void moveFish(int d, int r, int c, List<Integer>[][] newMap) {
			int rot = 0;
			int nd = d;
			while (rot++ < 8) {
				int nr = r + fDeltas[nd][0];
				int nc = c + fDeltas[nd][1];
				// System.out.printf("%d %d %d -> %d %d %d\n", r, c, d, nr, nc, nd);
				// if (isValid(nr, nc)) {
				// 	System.out.printf("%b %d\n", isShark(nr, nc), smell[nr][nc]);
				// }
				if (!isValid(nr, nc) || isShark(nr, nc) || smell[nr][nc] > 0) {
					nd = (nd - 1 + 8) % 8;
					continue;
				}
				newMap[nr][nc].add(nd);
				// System.out.printf("%d %d %d -> %d %d %d\n", r, c, d, nr, nc, nd);
				return;
			}
			newMap[r][c].add(nd);
		}

		private boolean isShark(int i, int j) {
			return i == shark[0] && j == shark[1];
		}

		private boolean isValid(int r, int c) {
			return 0 <= r && r < N && 0 <= c && c < N;
		}

		@SuppressWarnings("unchecked")
		private List<Integer>[][] createMap() {
			List<Integer>[][] map = (List<Integer>[][])new List[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = new ArrayList<>();
				}
			}
			return map;
		}

		private void copyMap(List<Integer>[][] newMap) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					newMap[i][j] = new ArrayList<>(map[i][j]);
				}
			}
		}
	}
}
