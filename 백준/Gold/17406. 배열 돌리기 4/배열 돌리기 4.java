import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] grid = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] commands = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				commands[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = Solution.solve(N, M, K, grid, commands);
		sb.append(answer);
		bw.write(sb.toString());

		bw.close();
		br.close();
	}

	static class Solution {
		static int N, M, K;
		static int[][] grid, copy;
		static int[] seq;
		static boolean[] visited;
		static List<int[]> comb;
		static int answer;

		public static int solve(int N, int M, int K, int[][] grid, int[][] commands) {
			init(N, M, K, grid);

			comb = new ArrayList<>();
			seq = new int[K];
			visited = new boolean[K];
			dfs(0);
			answer = Integer.MAX_VALUE;
			for (int[] commandSeq : comb) {
				// System.out.println("commandSeq = " + Arrays.toString(commandSeq));
				copy();
				// print();

				for (int cIdx : commandSeq) {
					int[] command = commands[cIdx];
					int r = command[0];
					int c = command[1];
					int s = command[2];
					rotate(r, c, s);
				}
				calc();

				// print();
			}
			return answer;
		}

		private static void copy() {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					grid[i][j] = copy[i][j];
				}
			}
		}

		private static void calc() {
			for (int r = 1; r <= N; r++) {
				int sum = 0;
				for (int c = 1; c <= M; c++) {
					sum += grid[r][c];
				}
				answer = Math.min(answer, sum);
			}
		}

		private static void dfs(int depth) {
			if (depth == K) {
				comb.add(seq.clone());
				return;
			}

			for (int i = 0; i < K; i++) {
				if (visited[i])
					continue;
				visited[i] = true;
				int prev = seq[depth];
				seq[depth] = i;
				dfs(depth + 1);
				seq[depth] = prev;
				visited[i] = false;
			}
		}

		private static void print() {
			for (int[] line : grid) {
				System.out.println(Arrays.toString(line));
			}
			System.out.println();
		}

		private static void rotate(int r, int c, int s) {
			for (int i = s; i > 0; i--) {
				int rs = r - i;
				int cs = c - i;
				int tmp = grid[rs][cs];
				for (int j = 0; j < i * 2; j++) {
					// System.out.printf("%d %d\n", rs + j, cs);
					grid[rs + j][cs] = grid[rs + j + 1][cs];
				}
				// System.out.println();
				for (int j = 0; j < i * 2; j++) {
					// System.out.printf("%d %d\n", rs + i * 2, cs + j);
					grid[rs + i * 2][cs + j] = grid[rs + i * 2][cs + j + 1];
				}
				// System.out.println();
				for (int j = 0; j < i * 2; j++) {
					// System.out.printf("%d %d\n", rs + i * 2 - j, cs + i * 2);
					grid[rs + i * 2 - j][cs + i * 2] = grid[rs + i * 2 - j - 1][cs + i * 2];
				}
				// System.out.println();
				for (int j = 0; j < i * 2; j++) {
					// System.out.printf("%d %d\n", rs, cs + i * 2 - j);
					grid[rs][cs + i * 2 - j] = grid[rs][cs + i * 2 - j - 1];
				}
				// System.out.println();
				grid[rs][cs + 1] = tmp;
			}
		}

		private static void init(int n, int m, int k, int[][] grid) {
			N = n;
			M = m;
			K = k;
			Solution.grid = new int[N + 1][M + 1];
			copy = grid;
		}
	}
}