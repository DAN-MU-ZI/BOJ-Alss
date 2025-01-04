import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		boolean[] dp = new boolean[N];
		dp[0] = true;

		for (int i = 1; i < N; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (dp[j]) {
					int cost = (i - j) * (1 + Math.abs(arr[i] - arr[j]));
					if (cost <= K) {
						dp[i] = true;
						break;
					}
				}
			}
		}

		bw.write(dp[N - 1] ? "YES" : "NO");
		bw.flush();
		bw.close();
	}
}
