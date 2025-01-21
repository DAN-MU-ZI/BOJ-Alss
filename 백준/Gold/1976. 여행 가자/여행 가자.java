import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] relation = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				relation[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] target = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			target[i] = Integer.parseInt(st.nextToken());
		}

		Solution solution = new Solution(N, M, relation, target);
		boolean result = solution.solve();
		sb.append(result ? "YES" : "NO");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private final int N;
		private final int M;
		private final int[][] relation;
		private final int[] target;

		public Solution(int N, int M, int[][] relation, int[] target) {
			this.N = N;
			this.M = M;
			this.relation = relation;
			this.target = target;
		}

		public boolean solve() {
			int[] parent = new int[N];
			for (int i = 0; i < N; i++) {
				parent[i] = i;
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (relation[i][j] == 1) {
						union(parent, i, j);
					}
				}
			}

			int root = find(parent, parent[target[0] - 1]);
			for (int i = 1; i < M; i++) {
				if (root != find(parent, target[i] - 1))
					return false;
			}
			return true;
		}

		private void union(int[] parent, int i, int j) {
			int rootX = find(parent, i);
			int rootY = find(parent, j);

			if (rootX < rootY)
				parent[rootY] = parent[rootX];
			else if (rootY < rootX)
				parent[rootX] = parent[rootY];
		}

		private int find(int[] parent, int i) {
			if (parent[i] != i)
				return find(parent, parent[i]);

			return parent[i];
		}
	}
}