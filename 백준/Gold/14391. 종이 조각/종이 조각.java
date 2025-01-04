import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}

		int answer = solution(N, M, arr);

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	public static int solution(int N, int M, int[][] arr) {
		int answer = 0;

		for (int bitmask = 0; bitmask < 1 << N * M; bitmask++) {
			int sum = 0;

			for (int row = 0; row < N; row++) {
				int rSum = 0;
				for (int col = 0; col < M; col++) {
					int index = row * M + col;
					if ((bitmask & (1 << index)) != 0) {
						rSum = rSum * 10 + arr[row][col];
					} else {
						sum += rSum;
						rSum = 0;
					}
				}
				sum += rSum;
			}

			for (int col = 0; col < M; col++) {
				int cSum = 0;
				for (int row = 0; row < N; row++) {
					int index = row * M + col;
					if ((bitmask & (1 << index)) == 0) {
						cSum = cSum * 10 + arr[row][col];
					} else {
						sum += cSum;
						cSum = 0;
					}
				}
				sum += cSum;
			}
			answer = Math.max(answer, sum);
		}
		return answer;
	}
}