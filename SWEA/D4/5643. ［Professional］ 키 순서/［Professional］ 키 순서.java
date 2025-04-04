import java.util.*;
import java.io.*;

class Solution {
    static int N, M;
    static Set<Integer>[] graph, rev;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            graph = new HashSet[N + 1];
            rev = new HashSet[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new HashSet<>();
                rev[i] = new HashSet<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // a보다 큰 애들을 담음
                graph[a].add(b);
                rev[b].add(a);
            }

            int answer = solve();
            sb.append(String.format("#%d %d\n", test_case, answer));
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int solve() {
        int answer = 0;

        for (int node = 1; node <= N; node++) {
            int backward = bfs(node, rev);
            int forward = bfs(node, graph);
            if (backward + forward == N - 1) {
                answer++;
            }
        }
        return answer;
    }

    static int bfs(int node, Set<Integer>[] graph) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> dq = new ArrayDeque<>();

        int cnt = 0;
        visited[node] = true;
        dq.add(node);

        while (!dq.isEmpty()) {
            int cur = dq.poll();

            for (int next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    dq.add(next);
                    cnt++;
                }
            }
        }

        return cnt;
    }
}