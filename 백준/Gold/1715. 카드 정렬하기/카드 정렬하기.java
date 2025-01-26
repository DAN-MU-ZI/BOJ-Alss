import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] cards = new int[N];
		for (int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(br.readLine());
			;
		}
		Solution solution = new Solution(N, cards);
		sb.append(solution.solve());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private final int N;
		private final int[] cards;

		public Solution(int N, int[] cards) {
			this.N = N;
			this.cards = cards;
		}

		public long solve() {
			PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.comparingLong(s -> s));

			for (int card : cards) {
				pq.add((long)card);
			}

			int count = N - 1;
			long answer = 0;
			while (count-- > 0) {
				long card1 = pq.poll();
				long card2 = pq.poll();

				long sum = card1 + card2;
				pq.add(sum);
				answer += sum;
			}

			return answer;
		}
	}
}