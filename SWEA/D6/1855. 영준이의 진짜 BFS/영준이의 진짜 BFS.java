import java.io.*;
import java.util.*;

class Solution {
    static int N, K;
    static int[] depth;
    static int[][] dp;
    static List<Integer>[] graph;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            K = (int) (Math.log(N)/Math.log(2)) + 1;

            depth = new int[N + 1];
            dp = new int[K][N + 1];
            graph = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }
            
            st = new StringTokenizer(br.readLine());
            for (int i = 2; i <= N; i++) {
                int p = Integer.parseInt(st.nextToken());
                
                depth[i] = depth[p] + 1;
                dp[0][i] = p;
                graph[p].add(i);
            }

            // initDP();

            long count = bfs(1);
            sb.append(String.format("#%d %d\n", t, count));
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static long bfs(int s) {
        long answer = 0;
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> dq = new ArrayDeque<>();

        visited[s] = true;
        dq.add(s);

        int cursor = 1;
        while (!dq.isEmpty()) {
            int node = dq.poll();

            answer += distance(cursor, node);
            cursor = node;

            for (int next: graph[node]) {
                if (!visited[next]) {
                    visited[next] = true;
                    dq.add(next);
                }
            }
        }

        return answer;
    }

    static long distance(int src, int dst) {
        int dS = depth[src];
        int dD = depth[dst];
        long dist = dS + dD;

        if (dS > dD) {
            while (dS != dD) {
                src = dp[0][src];
                dS = depth[src];
            }
        }
        if (dD > dS) {
            while (dD != dS) {
                dst = dp[0][dst];
                dD = depth[dst];
            }
        }

        while (src != dst) {
            src = dp[0][src];
            dst = dp[0][dst];
        }

        int p = src;
        int dP = depth[p];
        dist -= dP * 2;
        return dist;
    }

    // static void initDP() {
    //     for (int k = 1; k < K; k++) {
    //         for (int i = 0; i <= N; i++) {
    //             dp[k][i] = dp[k - 1][dp[k - 1][i]];
    //         }
    //     }
    // }
}
