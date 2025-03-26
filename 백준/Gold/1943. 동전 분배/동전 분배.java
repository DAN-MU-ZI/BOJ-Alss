import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int N;
        int[] dp;
        
        for (int t = 0; t < 3; t++) {
            N = Integer.parseInt(br.readLine());
            int tot = 0;
            dp = new int[500001];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int coin = Integer.parseInt(st.nextToken());
                int count = Integer.parseInt(st.nextToken());
                for (int j = 0; j <= count; j++) {
                	for (int k = tot; k >= coin; k--) {
                		if (k + (coin * j) > 50000) {
                			break;
                		}
                		
                		if (dp[k] > 0) {
                			dp[k + (coin * j)] += 1;
                		}
                	}
                }
                
                for (int j = 0; j <= count; j++) {
                	dp[coin * j] += 1;
                }
                
                tot += coin * count;
            }
            
            if(tot % 2 != 0) {
                sb.append(0).append("\n");
                continue;
            }
            
            
            
            sb.append(dp[tot / 2] != 0? 1 : 0).append("\n");
        }
        System.out.println(sb);
    }
}
