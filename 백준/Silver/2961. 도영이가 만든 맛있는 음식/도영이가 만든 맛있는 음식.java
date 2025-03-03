import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}

		bw.write(String.valueOf(solve()));
		bw.close();
		br.close();
	}

	static int solve() {
		int answer = Integer.MAX_VALUE;
		for (int mask = 1; mask < (1 << N); mask++) {
			int diff = calc(mask);
			answer = Math.min(answer, diff);
		}
		return answer;
	}

	static int calc(int mask) {
		int S = 1;
		int B = 0;
		for (int i = 0; i < N; i++) {
			if ((mask & (1 << i)) > 0) {
				S *= arr[i][0];
				B += arr[i][1];
			}
		}
		return Math.abs(S - B);
	}
}
