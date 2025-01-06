import java.io.*;
import java.util.*;

class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			int[][] arr = new int[N][N];

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			sb.append(String.format("#%d\n", test_case));
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					sb.append(arr[N - c - 1][r]);
				}
				sb.append(" ");

				for (int c = 0; c < N; c++) {
					sb.append(arr[N - r - 1][N - c - 1]);
				}
				sb.append(" ");

				for (int c = 0; c < N; c++) {
					sb.append(arr[c][N - r - 1]);
				}
				sb.append("\n");
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}