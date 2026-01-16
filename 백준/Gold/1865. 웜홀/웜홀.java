import java.io.*;
import java.util.*;

public class Main {
    public static class Edge {
        int node;
        int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int TC = Integer.parseInt(br.readLine());
        while (TC --> 0) {
            boolean isPossible = false;

            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            List<Edge>[] graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            // 도로
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                Edge e1 = new Edge(E, T);
                graph[S].add(e1);

                Edge e2 = new Edge(S, T);
                graph[E].add(e2);
            }

            // 웜홀
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());

                Edge e = new Edge(E, -T);
                graph[S].add(e);
            }

            Queue<Integer> q = new ArrayDeque<>();

            int[] costs = new int[N + 1];
            int[] visitCount = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                q.add(i);
            }


            while (!q.isEmpty()) {
                int cur = q.poll();

                for (Edge next: graph[cur]) {
                    if (costs[cur] + next.weight < costs[next.node]) {
                        costs[next.node] = costs[cur] + next.weight;
                        q.add(next.node);

                        visitCount[next.node]++;
                        if (visitCount[next.node] >= N) {
                            isPossible = true;
                            break;
                        }
                    }
                }
                if (isPossible) break;
            }


            sb.append(isPossible? "YES" : "NO").append("\n");
        }
        System.out.println(sb);
    }
}