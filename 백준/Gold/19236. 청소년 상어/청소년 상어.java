import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int[][] input = new int[4][8];
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 8; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Solution solution = new Solution(input);
		sb.append(solution.solve());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	public static class Fish {
		int direction;
		int num;

		public Fish(int num, int direction) {
			this.num = num;
			this.direction = direction;
		}
	}

	private static class Solution {
		private Fish[][] map;
		private int[][] fishes;
		private final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
		private final int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
		private int answer = 0;

		public Solution(int[][] input) {
			map = new Fish[4][4];
			fishes = new int[17][2];

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					int num = input[i][j * 2];
					int dir = input[i][j * 2 + 1] - 1;
					map[i][j] = new Fish(num, dir);
					fishes[num] = new int[] {i, j};
				}
			}
		}

		public int solve() {
			dfs(0, 0, 0);
			return answer;
		}

		private void dfs(int score, int r, int c) {
			Fish[][] mapBackup = copyMap();
			int[][] fishesBackup = copyFishes();

			Fish fish = map[r][c];
			score += fish.num;
			int d = fish.direction;
			fishes[fish.num] = null;
			map[r][c] = null;
			answer = Math.max(answer, score);

			moveFish(r, c);

			for (int i = 1; i <= 3; i++) {
				int nr = r + dr[d] * i;
				int nc = c + dc[d] * i;

				if (!isValid(nr, nc))
					break;
				if (map[nr][nc] == null)
					continue;
				dfs(score, nr, nc);
			}

			restoreState(mapBackup, fishesBackup);
		}

		private void moveFish(int sharkR, int sharkC) {
			for (int i = 1; i <= 16; i++) {
				int[] pos = fishes[i];
				if (pos == null) continue;

				Fish fish = map[pos[0]][pos[1]];
				int originalDir = fish.direction;
				for (int rot = 0; rot < 8; rot++) {
					int nd = (originalDir + rot) % 8;
					int nr = pos[0] + dr[nd];
					int nc = pos[1] + dc[nd];


					if (isValid(nr, nc) && !(nr == sharkR && nc == sharkC)) {
						fish.direction = nd;
						if (map[nr][nc] == null) {
							map[nr][nc] = fish;
							map[pos[0]][pos[1]] = null;
							fishes[i] = new int[]{nr, nc};
						} else {
							Fish dst = map[nr][nc];
							map[nr][nc] = fish;
							map[pos[0]][pos[1]] = dst;

							int[] tmp = new int[]{fishes[i][0], fishes[i][1]};
							fishes[i] = new int[]{nr, nc};
							fishes[dst.num] = tmp;
						}

						break;
					}
				}
			}
		}


		private Fish[][] copyMap() {
			Fish[][] newMap = new Fish[4][4];
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (map[i][j] != null) {
						newMap[i][j] = new Fish(map[i][j].num, map[i][j].direction);
					}
				}
			}
			return newMap;
		}

		private int[][] copyFishes() {
			int[][] newFishes = new int[17][];
			for (int i = 1; i <= 16; i++) {
				if (fishes[i] != null) {
					newFishes[i] = new int[]{fishes[i][0], fishes[i][1]};
				}
			}
			return newFishes;
		}

		private void restoreState(Fish[][] backupMap, int[][] backupFishes) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (backupMap[i][j] != null) {
						map[i][j] = new Fish(backupMap[i][j].num, backupMap[i][j].direction);
					} else {
						map[i][j] = null;
					}
				}
			}

			for (int i = 1; i <= 16; i++) {
				if (backupFishes[i] != null) {
					fishes[i] = new int[]{backupFishes[i][0], backupFishes[i][1]};
				} else {
					fishes[i] = null;
				}
			}
		}

		private boolean isValid(int r, int c) {
			return 0 <= r && r < 4 && 0 <= c && c < 4;
		}
	}
}