import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] innings = new int[N][9];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				innings[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = Solution.solve(N, innings);
		sb.append(answer);

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static class Solution {
		static int N, answer;
		static int[] visited;
		static int[][] innings;

		public static int solve(int N, int[][] innings) {
			init(N, innings);

			visited = new int[] {4, 0, 0, 0, 0, 0, 0, 0, 0};
			dfs(1);

			return answer;
		}

		static int score, base;

		static void simulate() {
			int[] players = new int[9];
			for (int i = 0; i < 9; i++) {
				players[visited[i] - 1] = i;
			}
			// System.out.println(Arrays.toString(visited));
			// System.out.println(Arrays.toString(players));

			int seq = 0;
			score = 0;
			int out;
			for (int round = 0; round < N; round++) {
				base = 0;
				out = 0;
				int[] inning = innings[round];
				while (out < 3) {
					int result = inning[players[seq]];

					seq = (seq + 1) % 9;
					if (result == 0) {
						out++;
					} else {
						hit(result);
					}
				}
			}

			answer = Math.max(answer, score);
		}

		static void hit(int count) {
			base |= (1 << 3);
			for (int i = 0; i < count; i++) {
				if ((base & 1) > 0) {
					score++;
				}
				base = base >> 1;
			}
		}

		static void dfs(int cur) {
			if (cur == 9) {
				simulate();
				return;
			}

			for (int i = 1; i < 9; i++) {
				if (visited[i] == 0) {
					if (cur >= 4)
						visited[i] = cur + 1;
					else
						visited[i] = cur;

					dfs(cur + 1);
					visited[i] = 0;
				}
			}
		}

		static void init(int N, int[][] innings) {
			Solution.N = N;
			Solution.innings = innings;
			answer = 0;
		}
	}
}

