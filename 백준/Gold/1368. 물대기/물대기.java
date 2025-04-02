import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int src, dst, cost;

        public Edge(int _src, int _dst, int _cost) {
            src = _src;
            dst = _dst;
            cost = _cost;
        }
    }

    static class Boruvka {
        int[] parent;

        public Boruvka(int n) {
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

        public boolean union(int x, int y) {
            int xroot = find(x);
            int yroot = find(y);

            if (xroot == yroot)
                return false;
            parent[yroot] = xroot;
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Edge> edges = new ArrayList();

        for (int i = 1; i <= N; i++) {
            int cost = Integer.parseInt(br.readLine());
            edges.add(new Edge(0, i, cost));
        }

        for (int i = 1; i <= N; i++) {
            String[] tokens = br.readLine().split(" ");
            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    int cost = Integer.parseInt(tokens[j - 1]);
                    edges.add(new Edge(i, j, cost));
                }
            }
        }

        int tot = 0;
        Boruvka bv = new Boruvka(N);
        int components = N + 1;

        while (components > 1) {
            Edge[] minEdge = new Edge[N + 1];

            for (Edge e : edges) {
                int u = bv.find(e.src);
                int v = bv.find(e.dst);

                if (u == v)
                    continue;

                if (minEdge[u] == null || minEdge[u].cost > e.cost) {
                    minEdge[u] = e;
                }
                if (minEdge[v] == null || minEdge[v].cost > e.cost) {
                    minEdge[v] = e;
                }
            }

            for (int i = 0; i <= N; i++) {
                Edge e = minEdge[i];
                if (e != null && bv.union(e.src, e.dst)) {
                    tot += e.cost;
                    components--;
                }
            }
        }

        System.out.println(tot);
    }
}
