import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int node;
        int cost;
        int time;

        public Edge(int node, int cost, int time) {
            this.node = node;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.cost == o.cost) return this.time - o.time;
            return this.cost - o.cost;
        }
    }

    static class State implements Comparable<State> {
        int node;
        int cost;
        int time;

        public State(int node, int cost, int time) {
            this.node = node;
            this.cost = cost;
            this.time = time;
        }

        @Override
        public int compareTo(State s) {
            if (this.time == s.time) return this.cost - s.cost;
            return this.time - s.time;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        List<Edge>[] graph = new ArrayList[100 + 1];
        int[][] dp = new int[100 + 1][10000 + 1];
        int[] minCost = new int[101];

        for (int i = 0; i <= 100; i++) {
            graph[i] = new ArrayList<>();
        }

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= N; i++) {
                graph[i].clear();
                minCost[i] = Integer.MAX_VALUE;
                Arrays.fill(dp[i], 0, M + 1, Integer.MAX_VALUE);
            }

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                graph[u].add(new Edge(v, c, d));
            }

            for (int i = 1; i <= N; i++) {
                Collections.sort(graph[i]);
            }

            PriorityQueue<State> pq = new PriorityQueue<>();
            pq.add(new State(1, 0, 0));
            dp[1][0] = 0;

            int ans = Integer.MAX_VALUE;

            while (!pq.isEmpty()) {
                State curr = pq.poll();

                if (curr.time > dp[curr.node][curr.cost]) continue;
                if (curr.cost >= minCost[curr.node]) continue;

                minCost[curr.node] = curr.cost;

                if (curr.node == N) {
                    ans = curr.time;
                    break;
                }

                for (Edge e : graph[curr.node]) {
                    int nextCost = curr.cost + e.cost;
                    int nextTime = curr.time + e.time;

                    if (nextCost > M) continue;

                    if (nextTime < dp[e.node][nextCost]) {
                        if (nextCost < minCost[e.node]) {
                            dp[e.node][nextCost] = nextTime;
                            pq.add(new State(e.node, nextCost, nextTime));
                        }
                    }
                }
            }

            if (ans == Integer.MAX_VALUE) {
                sb.append("Poor KCM");
            } else {
                sb.append(ans);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}