import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Country[] countries = new Country[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int bronze = Integer.parseInt(st.nextToken());

			Country country = new Country(idx, gold, silver, bronze);
			countries[i] = country;
		}
		Solution solution = new Solution(N, K, countries);
		int answer = solution.solve();

		StringBuilder sb = new StringBuilder();
		sb.append(answer);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Country {
		int idx;
		int gold;
		int silver;
		int bronze;

		public Country(int idx, int gold, int silver, int bronze) {
			this.idx = idx;
			this.gold = gold;
			this.silver = silver;
			this.bronze = bronze;
		}

		@Override
		public String toString() {
			return "Country{" +
				"idx=" + idx +
				", gold=" + gold +
				", silver=" + silver +
				", bronze=" + bronze +
				'}';
		}
	}

	private static class Solution {
		private final int N;
		private final int K;
		private final Country[] countries;

		public Solution(int N, int K, Country[] countries) {
			this.N = N;
			this.K = K;
			this.countries = countries;
		}

		public int solve() {
			Comparator<Country> comparator = ((s1, s2) -> {
				int cmp;

				cmp = Integer.compare(s1.gold, s2.gold);
				if (cmp!=0) return cmp;

				cmp = Integer.compare(s1.silver, s2.silver);
				if (cmp!=0) return cmp;

				cmp = Integer.compare(s1.bronze, s2.bronze);

				return cmp;
			});
			Queue<Country> pq = new PriorityQueue<>(comparator);

			pq.addAll(Arrays.asList(countries));

			int rank = 1;
			Stack<Country> stk = new Stack<>();
			while(!pq.isEmpty()){
				Country cur = pq.poll();

				if (stk.isEmpty()) {
					stk.add(cur);
					continue;
				}

				Country prev = stk.peek();
				if (comparator.compare(prev, cur) != 0){
					stk = new Stack<>();
					rank++;
				}

				if (cur.idx==K) {
					break;
				}

				stk.add(cur);
			}

			return rank;
		}
	}
}