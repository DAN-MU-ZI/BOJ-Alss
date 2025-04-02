import java.io.*;
import java.util.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int src, dst, cost;

        public Edge(int _src, int _dst, int _cost) {
            src = _src;
            dst = _dst;
            cost = _cost;
        }

        public int compareTo(Edge e) {
            return cost - e.cost;
        }
    }

    static class UnionFinder {
        int[] parent;
        int[] rank;

        public UnionFinder(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int xroot = find(x);
            int yroot = find(y);

            if (xroot == yroot)
                return;

            int cmp = compareRank(xroot, yroot);
            if (cmp < 0) {
                parent[xroot] = yroot;
            } else if (cmp > 0) {
                parent[yroot] = xroot;
            } else {
                parent[yroot] = xroot;
                rank[xroot]++;
            }
        }

        public int compareRank(int x, int y) {
            return rank[x] - rank[y];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int cost = Integer.parseInt(br.readLine());
            pq.add(new Edge(N, i, cost));
        }

        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split(" ");
            for (int j = i + 1; j < N; j++) {
                pq.add(new Edge(i, j, Integer.parseInt(tokens[j])));
            }
        }

        int answer = 0;
        UnionFinder uf = new UnionFinder(N);
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int xroot = uf.find(edge.src);
            int yroot = uf.find(edge.dst);

            if (xroot != yroot) {
                answer += edge.cost;
                uf.union(xroot, yroot);
            }
        }

        System.out.println(answer);
    }
}
