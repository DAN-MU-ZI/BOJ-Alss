import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		boolean[] dp = new boolean[N + 1];
		dp[1] = true;
		if (N >= 2)
			dp[2] = false;
		if (N >= 3)
			dp[3] = true;

		for (int i = 4; i <= N; i++) {
			dp[i] = !dp[i - 1] || !dp[i - 3];
		}

		bw.write(dp[N] ? "SK" : "CY");
		bw.flush();
		bw.close();
	}
}
