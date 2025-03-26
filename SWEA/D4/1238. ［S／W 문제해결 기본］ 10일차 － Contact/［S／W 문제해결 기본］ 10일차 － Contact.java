import java.io.*;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int test_case = 1; test_case <= 10; test_case++) {
            List<Integer>[] graph = new ArrayList[101];
            for (int i = 1; i <= 100; i++) {
                graph[i] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            int N, S;
            N = Integer.parseInt(st.nextToken());
            S = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N / 2; i++) {
                int src = Integer.parseInt(st.nextToken());
                int dst = Integer.parseInt(st.nextToken());

                graph[src].add(dst);
            }

            int depth = 0;
            int num = S;

            Queue<int[]> dq = new ArrayDeque<>();
            boolean[] visited = new boolean[101];

            dq.add(new int[] { S, 0 });
            visited[S] = true;

            while (!dq.isEmpty()) {
                int[] cur = dq.poll();
                int node = cur[0];
                int d = cur[1];

                if (depth < d) {
                    depth = d;
                    num = node;
                } else if (depth == d && num < node) {
                    num = node;
                }

                for (int next : graph[node]) {
                    int nextDepth = d + 1;
                    if (!visited[next]) {
                        visited[next] = true;
                        dq.add(new int[] { next, nextDepth });
                    }
                }
            }

            sb.append(String.format("#%d %d\n", test_case, num));
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}