import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        tree = new int[4 * N + 1];
        build(1, 1, N);

        M = Integer.parseInt(br.readLine());
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int choice = Integer.parseInt(st.nextToken());
            if (choice == 1) {
                int i = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                arr[i] = v;
                update(1, 1, N, i, i);
            } else if (choice == 2) {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int res = find(1, 1, N, i, j);
                sb.append(res + "\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void update(int node, int start, int end, int target, int value) {
        if (start == end) {
            tree[node] = value;
            return;
        }

        int mid = (start + end) / 2;
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;

        if (target <= mid) {
            update(leftNode, start, mid, target, value);
        } else {
            update(rightNode, mid + 1, end, target, value);
        }

        tree[node] = max(tree[leftNode], tree[rightNode]);
    }

    static final int INF = Integer.MAX_VALUE;

    static int find(int node, int start, int end, int L, int R) {
        if (start > R || end < L) {
            return INF;
        }

        if (L <= start && end <= R) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;

        return max(find(leftNode, start, mid, L, R), find(rightNode, mid + 1, end, L, R));
    }

    static int max(int leftIdx, int rightIdx) {
        if (leftIdx != INF && rightIdx == INF) {
            return leftIdx;
        }
        if (leftIdx == INF && rightIdx != INF) {
            return rightIdx;
        }

        if (compareTo(leftIdx, rightIdx)) {
            return rightIdx;
        }
        return leftIdx;
    }

    static boolean compareTo(int a, int b) {
        int an = arr[a];
        int bn = arr[b];

        return an - bn > 0;
    }

    static void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = start;
            return;
        }

        int mid = (start + end) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;
        build(leftNode, start, mid);
        build(rightNode, mid + 1, end);

        tree[node] = max(tree[leftNode], tree[rightNode]);
    }
}
