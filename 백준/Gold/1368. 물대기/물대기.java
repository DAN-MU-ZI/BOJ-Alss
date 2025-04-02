import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int node, cost;

        public Edge(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Edge>[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            int cost = Integer.parseInt(br.readLine());
            graph[0].add(new Edge(i, cost));
            graph[i].add(new Edge(0, cost));
        }

        for (int i = 1; i <= N; i++) {
            String[] tokens = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                int cost = Integer.parseInt(tokens[j - 1]);
                if (i != j) {
                    graph[i].add(new Edge(j, cost));
                }
            }
        }

        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));
        int totalCost = 0;

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            int node = curr.node;
            int cost = curr.cost;

            if (visited[node])
                continue;
            visited[node] = true;
            totalCost += cost;

            for (Edge next : graph[node]) {
                if (!visited[next.node]) {
                    pq.add(new Edge(next.node, next.cost));
                }
            }
        }

        System.out.println(totalCost);
    }
}
