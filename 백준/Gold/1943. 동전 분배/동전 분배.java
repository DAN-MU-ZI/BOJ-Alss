import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int N;
        int[][] coins;
        int[] dp;
        
        for (int t = 0; t < 3; t++) {
            N = Integer.parseInt(br.readLine());
            coins = new int[N][2];
            int tot = 0;
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int coin = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                coins[i] = new int[] {coin, count};
                
                tot += coin * count;
            }
            
            if(tot % 2 != 0) {
                sb.append(0).append("\n");
                continue;
            }
            
            dp = new int[500001];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;
            
            for (int i = 0; i < N; i++) {
            	int coin = coins[i][0];
            	int count = coins[i][1];
            	
            	for (int k = 0; k <= tot / 2; k++) {
        			if (dp[k] == Integer.MAX_VALUE) continue;
        			
        			if (k + coin <= tot / 2 && dp[k] < count) {
        				dp[k + coin] = Math.min(dp[k + coin], dp[k] + 1);
        			}
        			
        			dp[k] = 0;
            	}
            }
            
            sb.append(dp[tot / 2] == Integer.MAX_VALUE ? 0 : 1).append("\n");
        }
        System.out.println(sb);
    }
}
