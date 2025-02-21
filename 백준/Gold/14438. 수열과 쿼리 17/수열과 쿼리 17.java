import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] arr, segtree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        segtree = new int[4 * N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        build(1, 1, N);

        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                int i = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                update(i, v);
            } else if (command == 2) {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                sb.append(query(1, 1, N, i, j) + "\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void build(int node, int start, int end) {
        if (start == end) {
            segtree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        build(leftChild, start, mid);
        build(rightChild, mid + 1, end);

        segtree[node] = Math.min(segtree[leftChild], segtree[rightChild]);
    }

    static void update(int i, int v) {
        update(1, 1, N, i, v);
    }

    static void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            segtree[node] = val;
            return;
        }

        int mid = (start + end) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        if (idx <= mid) {
            update(leftChild, start, mid, idx, val);
        } else {
            update(rightChild, mid + 1, end, idx, val);
        }

        segtree[node] = Math.min(segtree[leftChild], segtree[rightChild]);
    }

    static int query(int i, int j) {
        return query(1, 1, N, i, j);
    }

    static int query(int node, int start, int end, int L, int R) {
        if (R < start || end < L) {
            return 1_000_000_000;
        }

        if (L <= start && end <= R) {
            return segtree[node];
        }

        int mid = (start + end) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;
        
        return Math.min(query(leftChild, start, mid, L, R), query(rightChild, mid + 1, end, L, R));
    }
}