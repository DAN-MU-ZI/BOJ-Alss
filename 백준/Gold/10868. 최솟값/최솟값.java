import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        tree = new int[4 * N];
        Arrays.fill(tree, Integer.MAX_VALUE);
        for (int i = 1; i <= N; i++) {
            build(i, Integer.parseInt(br.readLine()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(query(a, b) + "\n");
        }
        System.out.println(sb);
    }

    static int query(int L, int R) {
        return query(1, 1, N, L, R);
    }

    static int query(int node, int start, int end, int L, int R) {
        if (R < start || end < L) {
            return Integer.MAX_VALUE;
        }

        if (L <= start && end <= R) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;

        return Math.min(query(leftNode, start, mid, L, R), query(rightNode, mid + 1, end, L, R));
    }

    static void build(int i, int value) {
        build(1, 1, N, i, value);
    }

    static void build(int node, int start, int end, int idx, int value) {
        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;

        if (idx <= mid) {
            build(leftNode, start, mid, idx, value);
        } else {
            build(rightNode, mid + 1, end, idx, value);
        }

        tree[node] = Math.min(tree[leftNode], tree[rightNode]);
    }
}