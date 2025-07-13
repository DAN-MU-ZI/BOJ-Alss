import java.io.*;
import java.util.*;

public class Main {
    static int N, Q;
    static long[] arr, tree;

    static void build(int node, int left, int right) {
        if (left == right) {
            tree[node] = arr[left];
            return;
        }
        int mid = (left + right) / 2;
        build(2 * node, left, mid);
        build(2 * node + 1, mid + 1, right);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    static void update(int node, int left, int right, int idx, int value) {
        if (idx < left || idx > right)
            return;
        if (left == right) {
            tree[node] = value;
            return;
        }
        int mid = (left + right) / 2;
        update(2 * node, left, mid, idx, value);
        update(2 * node + 1, mid + 1, right, idx, value);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    static long query(int node, int left, int right, int l, int r) {
        if (r < left || right < l)
            return 0;
        if (l <= left && right <= r)
            return tree[node];
        int mid = (left + right) / 2;
        return query(2 * node, left, mid, l, r) + query(2 * node + 1, mid + 1, right, l, r);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        arr = new long[N];
        tree = new long[N * 4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        build(1, 0, N - 1);

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            int l = Math.min(x, y);
            int r = Math.max(x, y);

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken());

            bw.write(query(1, 0, N - 1, l, r) + "\n");
            update(1, 0, N - 1, a, b);
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
