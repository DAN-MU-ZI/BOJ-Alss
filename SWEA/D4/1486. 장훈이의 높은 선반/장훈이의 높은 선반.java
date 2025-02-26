import java.util.*;
import java.io.*;

class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			int[] H = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}
			int answer = 10000 * N;
			int total = 0;
			for (int i = 1; i < 1 << N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					if ((i & (1 << j)) > 0)
						sum = sum + H[j];
				}
				if (B <= sum)
					answer = Math.min(answer, sum);
				total = Math.max(total, sum);
			}
			sb.append(String.format("#%d %d\n", test_case, answer - B));
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}