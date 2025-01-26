import java.io.*;
import java.util.*;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		String A = st.nextToken();
		int P = Integer.parseInt(st.nextToken());
		Solution solution = new Solution(A, P);
		sb.append(solution.solve());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		private final String A;
		private final int P;

		public Solution(String a, int p) {
			this.A = a;
			this.P = p;
		}

		public int solve() {
			final int SIZE = 1000;
			String[] arr = new String[SIZE];
			arr[0] = A;

			for (int i = 1; i < SIZE; i++) {
				int sum = 0;
				for (char c : arr[i - 1].toCharArray()) {
					sum += (int)Math.pow(c - '0', P);
				}
				arr[i] = String.valueOf(sum);
			}
			// System.out.println(Arrays.toString(arr));

			int dupStart = -1;
			FindDup:
			for (int start = 0; start < SIZE; start++) {
				for (int term = 1; start + term * 2 < SIZE; term++) {
					String[] base = new String[term];
					for (int i = 0; i < term; i++) {
						base[i] = arr[start + i];
					}

					boolean isDup = true;
					DupBreak:
					for (int slide = start + term; slide + term < SIZE; slide += term) {
						for (int i = 0; i < term; i++) {
							if (!arr[slide + i].equals(base[i])){
								isDup = false;
								break DupBreak;
							}
						}
					}
					if (isDup){
						dupStart = start;
						break FindDup;
					}
				}
			}

			return dupStart;
		}
	}
}