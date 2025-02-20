import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static int[][] inputs;
    static long[] tree, lazy;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        inputs = new int[M][];
        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if (num == 1) {
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                inputs[idx] = new int[] {num, i, j, k};
            } else if (num == 2) {
                int x = Integer.parseInt(st.nextToken());
                inputs[idx] = new int[] {num, x};
            }
        }

        String answer = solve();
        bw.write(answer);

        bw.close();
        br.close();
    }

    static void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        int leftChild = node * 2;
        int rightChild = node * 2 + 1;

        build(leftChild, start, mid);
        build(rightChild, mid + 1, end);

        tree[node] = tree[leftChild] + tree[rightChild];
    }

    static void add(int i, int j, int k) {
        update(1, 1, N, i, j, k);
    }

    static void pushDown(int node, int start, int end) {
        if (lazy[node] != 0) {
            int mid = (start + end) / 2;
            int leftChild = node * 2;
            int rightChild = node * 2 + 1;

            tree[node] += (end - start + 1) * lazy[node];

            if (start != end) {
                lazy[leftChild] += lazy[node];
                lazy[rightChild] += lazy[node];
            }

            lazy[node] = 0;
        }
    }

    static void update(int node, int start, int end, int L, int R, int k) {
        pushDown(node, start, end);

        if (end < L || start > R) return;

        if (L <= start && end <= R) {
            lazy[node] += k;
            pushDown(node, start, end);
            return;
        }

        int mid = (start + end) / 2;
        int leftChild = node * 2;
        int rightChild = node * 2 + 1;

        update(leftChild, start, mid, L, R, k);
        update(rightChild, mid + 1, end, L, R, k);

        tree[node] = tree[leftChild] + tree[rightChild];
    }

    static long get(int x) {
        return find(1, 1, N, x);
    }    

    static long find(int node, int start, int end, int x) {
        pushDown(node, start, end);

        if (start == end) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        if (x <= mid) {
            int leftChild = node * 2;
            return find(leftChild, start, mid, x);
        } else {
            int rightChild = node * 2 + 1;
            return find(rightChild, mid + 1, end, x);
        }
    }

    static String solve() {
        StringBuilder sb = new StringBuilder();
        
        tree = new long[4 * N + 1];
        lazy = new long[4 * N + 1];
        build(1, 1, N);

        for (int[] input: inputs) {
            int num = input[0];

            if (num == 1) {
                int i = input[1];
                int j = input[2];
                int k = input[3];
                add(i, j, k);
            } else if (num == 2) {
                int x = input[1];
                sb.append(get(x) + "\n");
            }
        }

        return sb.toString();
    }

    
}