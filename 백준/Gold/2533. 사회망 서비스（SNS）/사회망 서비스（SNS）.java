import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Integer>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        bw.write(String.valueOf(solve()));
        
        bw.close();
        br.close();
    }

    static boolean[] visited;
    static int[][] dp;
    static int solve() {
        visited = new boolean[N + 1];
        dp = new int[N + 1][2];
        
        dfs(1);
        return Math.min(dp[1][0], dp[1][1]);
    }

    static void dfs(int cur) {
        visited[cur] = true;
        dp[cur][1] = 1;
        for (int next: graph[cur]) {
            if (!visited[next]) {
                dfs(next);
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
                dp[cur][0] += dp[next][1];
            }
        }
    }
}