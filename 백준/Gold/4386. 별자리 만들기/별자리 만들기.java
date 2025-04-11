import java.io.*;
import java.util.*;

public class Main {
    static class Star {
        double x, y;

        Star(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        int a, b;
        double dist;

        Edge(int a, int b, double dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }

        public int compareTo(Edge other) {
            return Double.compare(this.dist, other.dist);
        }
    }

    static int[] parent;

    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB)
            parent[rootB] = rootA;
    }

    static double getDistance(Star s1, Star s2) {
        double dx = s1.x - s2.x;
        double dy = s1.y - s2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Star[] stars = new Star[n];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            double x = Double.parseDouble(input[0]);
            double y = Double.parseDouble(input[1]);
            stars[i] = new Star(x, y);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = getDistance(stars[i], stars[j]);
                pq.offer(new Edge(i, j, dist));
            }
        }

        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;

        double totalCost = 0.0;
        int edgeCount = 0;

        while (edgeCount < n - 1 && !pq.isEmpty()) {
            Edge e = pq.poll();
            if (find(e.a) != find(e.b)) {
                union(e.a, e.b);
                totalCost += e.dist;
                edgeCount++;
            }
        }

        System.out.printf("%.2f\n", totalCost);
    }
}
