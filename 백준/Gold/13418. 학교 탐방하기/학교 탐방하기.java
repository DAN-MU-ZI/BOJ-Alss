import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int node, weight;

        public Edge(int _node, int _weight) {
            node = _node;
            weight = _weight;
        }
    }

    static int N, M;
    static ArrayList<Edge>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));
        }

        PriorityQueue<Edge> pq;

        pq = new PriorityQueue<>((s1, s2) -> s1.weight - s2.weight);
        int worst = prim(pq, 0);
        pq = new PriorityQueue<>((s1, s2) -> s2.weight - s1.weight);
        int best = prim(pq, 0);

        System.out.println(worst * worst - best * best);
    }

    static int prim(PriorityQueue<Edge> pq, int start) {
        boolean[] visited = new boolean[N + 1];
        pq.offer(new Edge(start, -1));

        int totalCost = 0;
        int edgeCount = 0;

        while (!pq.isEmpty() && edgeCount < N) {
            Edge cur = pq.poll();
            if (visited[cur.node])
                continue;

            visited[cur.node] = true;
            if (cur.weight == 0)
                totalCost++;
            if (cur.weight != -1)
                edgeCount++;

            for (Edge edge : graph[cur.node]) {
                if (!visited[edge.node]) {
                    pq.offer(edge);
                }
            }
        }

        return totalCost;
    }
}
