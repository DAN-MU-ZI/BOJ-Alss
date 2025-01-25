import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		Solution solution = new Solution(R, C, map);
		sb.append(solution.solve());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class UnionFind {
		private final int[] parent;

		private UnionFind(int size) {
			parent = new int[size];
			for (int i = 0; i < size; i++) {
				parent[i] = i;
			}
		}

		public int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);
			}
			return parent[x];
		}

		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);
			if (rootX == rootY)
				return;

			if (parent[rootX] < parent[rootY]) {
				parent[rootY] = rootX;
			} else if (parent[rootY] < parent[rootX]) {
				parent[rootX] = rootY;
			}
		}

		public boolean isConnected(int x, int y) {
			return find(x) == find(y);
		}
	}

	private static class Solution {
		private final int R, C;
		private final char[][] map;
		UnionFind uf;
		int swan1 = -1;
		int swan2 = -1;
		Queue<Integer> waterQueue;
		private final int[] dr = {1, 0, -1, 0};
		private final int[] dc = {0, 1, 0, -1};

		public Solution(int R, int C, char[][] map) {
			this.R = R;
			this.C = C;
			this.map = map;
			this.uf = new UnionFind(R * C);
			this.waterQueue = new ArrayDeque<>();
			initialize();
		}

		public int solve() {
			int swan1Index = swan1;
			int swan2Index = swan2;
			if (uf.isConnected(swan1Index, swan2Index)) {
				return 0;
			}

			int days = 0;
			while (true) {
				days++;
				meltIce();
				if (uf.isConnected(swan1Index, swan2Index)) {
					return days;
				}
			}
		}

		private void initialize() {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					int pos = i * C + j;
					if (map[i][j] != 'X') {
						waterQueue.offer(pos);
						unionAdgacent(i, j, pos);
					}
					if (map[i][j] == 'L') {
						if (swan1 == -1) {
							swan1 = pos;
						} else {
							swan2 = pos;
						}
					}
				}
			}
		}

		private void unionAdgacent(int i, int j, int pos) {
			for (int d = 0; d < 4; d++) {
				int ni = i + dr[d];
				int nj = j + dc[d];
				if (isValid(ni, nj) && map[ni][nj] != 'X') {
					int neighbor = ni * C + nj;
					uf.union(pos, neighbor);
				}
			}
		}

		private void meltIce() {
			int size = waterQueue.size();
			for (int i = 0; i < size; i++) {
				int current = waterQueue.poll();
				int r = current / C;
				int c = current % C;
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (isValid(nr, nc) && map[nr][nc] == 'X') {
						map[nr][nc] = '.';
						int neighbor = nr * C + nc;
						waterQueue.offer(neighbor);
						unionAdgacent(nr, nc, neighbor);
					}
				}
			}
		}

		private boolean isValid(int r, int c) {
			return r >= 0 && r < R && c >= 0 && c < C;
		}
	}
}