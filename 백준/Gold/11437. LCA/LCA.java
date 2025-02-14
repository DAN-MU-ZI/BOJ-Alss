import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] depth;
    static int[][] dp;
    static List<Integer>[] edges;

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N  = Integer.parseInt(br.readLine());
        K = K = (int)(Math.floor(Math.log(N) / Math.log(2))) + 1;

        depth = new int[N + 1];
        dp = new int[K][N + 1];

        edges = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        // 무방향 그래프에서 트리구조 만들기
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }

        bfs(1);

        init();

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int dA = depth[a];
            int dB = depth[b];

            if (dA > dB) {
                for (int k = K - 1; k >= 0; k--) {
                    if (depth[a] - (1 << k) >= depth[b]) {
                        a = dp[k][a];
                    }
                }
            } 
            if (dB > dA) {
                for (int k = K - 1; k >= 0; k--) {
                    if (depth[b] - (1 << k) >= depth[a]) {
                        b = dp[k][b];
                    }
                }
            }

            if (a != b) {
                for (int k = K - 1; k >= 0; k--) {
                    if (dp[k][a] != dp[k][b]) {
                        a = dp[k][a];
                        b = dp[k][b];
                    }
                }
                sb.append(dp[0][a] + "\n");
            } else {
                sb.append(a + "\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void init() {
        for (int i = 1; i < K; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = dp[i-1][dp[i-1][j]];
            }
        }
    }

    static void bfs(int s) {
        boolean[] visited = new boolean[N + 1];
        Queue<int[]> dq = new ArrayDeque<>();
        
        dq.add(new int[]{s, 1});
        visited[s] = true;
        depth[s] = 1;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int c = cur[0];
            int d = cur[1];

            for (int next: edges[c]) {
                if (c == next || visited[next]) continue;
                dp[0][next] = c;
                
                depth[next] = d + 1;
                dq.add(new int[]{next, d + 1});
                visited[next] = true;
            }
        }
    }

}