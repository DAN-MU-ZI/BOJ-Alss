import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int score = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		if (N > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
		}

		Solution solution = new Solution(N, score, P, arr);
		sb.append(solution.solve());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		int n;
		int score;
		int p;
		int[] arr;

		public Solution(int n, int score, int p, int[] arr) {
			this.n = n;
			this.score = score;
			this.p = p;
			this.arr = arr;
		}

		public int solve() {
			if (n == 0) {
				return 1;
			}

			if (n == p && score <= arr[n - 1]) {
				return -1;
			}

			List<Integer> scoreList = new ArrayList<>();
			for (int num : arr) {
				scoreList.add(num);
			}
			scoreList.add(score);

			scoreList.sort(Collections.reverseOrder());

			int rank = 1;
			for (int i = 0; i < scoreList.size(); i++) {
				if (scoreList.get(i) > score) {
					rank++;
				} else {
					break;
				}
			}

			if (rank > p) {
				return -1;
			}

			return rank;
		}
	}
}
