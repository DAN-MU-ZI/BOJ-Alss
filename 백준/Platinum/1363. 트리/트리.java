import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[] keys = new int[k + 2];
        for (int i = 1; i <= k; i++) {
            keys[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(keys, 1, k + 1);

        keys[0] = 0;
        keys[k + 1] = n + 1;

        long[][] dp = new long[k + 2][k + 2];

        for (int len = 1; len <= k; len++) {
            for (int i = 1; i <= k - len + 1; i++) {
                int j = i + len - 1;

                dp[i][j] = Long.MAX_VALUE;

                int weight = keys[j + 1] - keys[i - 1] - 1;

                for (int root = i; root <= j; root++) {
                    long leftCost = dp[i][root - 1];
                    long rightCost = dp[root + 1][j];

                    if (leftCost + rightCost + weight < dp[i][j]) {
                        dp[i][j] = leftCost + rightCost + weight;
                    }
                }
            }
        }

        System.out.println(dp[1][k]);
    }
}