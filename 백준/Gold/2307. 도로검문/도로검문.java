import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int idx;
        int cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }

    static int N, M;
    static int[] costs;
    static int[] parent;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        costs = new int[N + 1];
        parent = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, w));
            graph[v].add(new Node(u, w));
        }

        int bestDistance = dijkstra(0, 0, true);
        if (bestDistance == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        List<int[]> pathEdges = new ArrayList<>();
        int curr = N;
        while (curr != 1) {
            int pre = parent[curr];
            pathEdges.add(new int[]{pre, curr});
            curr = pre;
        }

        int maxDelay = 0;
        for (int[] edge : pathEdges) {
            int blockedDistance = dijkstra(edge[0], edge[1], false);

            if (blockedDistance == Integer.MAX_VALUE) {
                System.out.println("-1");
                return;
            }

            int delay = blockedDistance - bestDistance;
            maxDelay = Math.max(maxDelay, delay);
        }
        System.out.println(maxDelay);
    }

    static int dijkstra(int block1, int block2, boolean isFirst) {
        Queue<Node> q = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[1] = 0;
        q.add(new Node(1, 0));

        while (!q.isEmpty()) {
            Node current = q.poll();

            if (dist[current.idx] < current.cost) continue;

            for (Node next : graph[current.idx]) {
                if ((current.idx == block1 && next.idx == block2) ||
                        (current.idx == block2 && next.idx == block1)) {
                    continue;
                }

                if (dist[next.idx] > dist[current.idx] + next.cost) {
                    dist[next.idx] = dist[current.idx] + next.cost;
                    q.add(new Node(next.idx, dist[next.idx]));

                    if (isFirst) {
                        parent[next.idx] = current.idx;
                    }
                }
            }
        }

        return dist[N];
    }
}