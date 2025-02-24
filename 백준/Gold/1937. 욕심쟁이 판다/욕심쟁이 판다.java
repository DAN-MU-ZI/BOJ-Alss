import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] grid, dp;
    static int[][] deltas = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
        
        bw.write(String.valueOf(ans));
        bw.close();
        br.close();
    }
    
    public static int dfs(int r, int c) {
        if (dp[r][c] != 0) {
            return dp[r][c];
        }
        
        dp[r][c] = 1;
        
        for (int[] delta : deltas) {
            int nr = r + delta[0];
            int nc = c + delta[1];
            
            if (isValid(nr, nc) && grid[nr][nc] > grid[r][c]) {
                dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
            }
        }
        
        return dp[r][c];
    }
    
    public static boolean isValid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}
