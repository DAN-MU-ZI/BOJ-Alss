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
			while (S-- > 0) {
				// 매 루프마다 copy를 새로 만들어주어야 함
				copy = createMap();
				copyMap(copy);

				moveFishes();
				moveShark();
				removeSmell();
				pasteMap();
			}
			int answer = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					answer += map[i][j].size();
				}
			}
			return answer;
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
		int[] find, fixedD;  // 'fixed'라는 이름이 예약어는 아니지만 혼동될 수 있어 변경
		int best;

		private void moveShark() {
			find = new int[3];
			fixedD = new int[3];
			best = -1;
			dfs(0);

			int nr = shark[0];
			int nc = shark[1];
			for (int d : fixedD) {
				nr += sDeltas[d][0];
				nc += sDeltas[d][1];
				if (!map[nr][nc].isEmpty()) {
					map[nr][nc].clear();
					smell[nr][nc] = 3;
				}
			}
			shark = new int[] {nr, nc};
		}

		private void dfs(int depth) {
			if (depth == 3) {
				boolean[][] visited = new boolean[N][N];
				int nr = shark[0];
				int nc = shark[1];
				int score = map[nr][nc].size();

				for (int d : find) {
					nr += sDeltas[d][0];
					nc += sDeltas[d][1];

					// 유효 범위 확인
					if (!isValid(nr, nc)) {
						return;
					}

					if (!visited[nr][nc]) {
						score += map[nr][nc].size();
					}
					visited[nr][nc] = true;
				}

				if (best < score) {
					System.arraycopy(find, 0, fixedD, 0, 3);
					best = score;
				}
				return;
			}

			for (int i = 0; i < 4; i++) {
				find[depth] = i;
				dfs(depth + 1);
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
				if (!isValid(nr, nc) || isShark(nr, nc) || smell[nr][nc] > 0) {
					nd = (nd - 1 + 8) % 8;
					continue;
				}
				newMap[nr][nc].add(nd);
				return;
			}
			// 이동 불가능하면 기존 위치에 그대로 복사
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
			List<Integer>[][] map = (List<Integer>[][]) new List[N][N];
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
