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

    static class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            parent = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX < rootY) {
                parent[rootY] = rootX;
            } else if (rootX > rootY) {
                parent[rootX] = rootY;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[] nodes = new char[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nodes[i] = st.nextToken().charAt(0);
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (nodes[u] == nodes[v])
                continue;

            pq.add(new Edge(u, v, d));
        }

        UnionFind uf = new UnionFind(N);

        int cost = 0;
        int usedEdges = 0;
        while (!pq.isEmpty() && usedEdges < N - 1) {
            Edge e = pq.poll();
            int rootX = uf.find(e.src);
            int rootY = uf.find(e.dst);

            if (rootX == rootY)
                continue;

            cost += e.cost;
            usedEdges++;
            uf.union(rootX, rootY);
        }

        if (usedEdges == N - 1) {
            System.out.println(cost);
        } else {
            System.out.println(-1);
        }
    }
}
