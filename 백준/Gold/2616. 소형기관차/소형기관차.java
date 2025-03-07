import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] sum = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        int K = Integer.parseInt(br.readLine());
        int[][] dp = new int[4][n + 1];
        for (int i = 1; i <= 3; i++) {
            for (int j = i * K; j <= n; j++) {
                int s = sum[j] - sum[j - K];
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - K] + s);
            }
        }

        bw.write(String.valueOf(String.valueOf(dp[3][n])));
        bw.close();
        br.close();
    }
}
