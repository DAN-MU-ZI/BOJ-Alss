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
                int x = Integer.parseInt(st.nextToken());
                update(1, 1, N, i, x);
            } else if (choice == 2) {
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int res = find(1, 1, N, l, r);
                sb.append((r - l + 1 - res) + "\n");
            } else if (choice == 3) {
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                int res = find(1, 1, N, l, r);
                sb.append(res + "\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    // 홀수수의 수 업데이트
    static void update(int node, int start, int end, int target, int val) {
        if (start == end) {
            tree[node] = val % 2;
            return;
        }

        int mid = (start + end) / 2;
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;

        if (target <= mid) {
            update(leftNode, start, mid, target, val);
        } else {
            update(rightNode, mid + 1, end, target, val);
        }

        tree[node] = tree[leftNode] + tree[rightNode];
    }

    static final int NAN = 0;

    // 짝수의 수 찾기
    static int find(int node, int start, int end, int L, int R) {
        if (start > R || end < L) {
            return NAN;
        }

        if (L <= start && end <= R) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;

        return find(leftNode, start, mid, L, R) + find(rightNode, mid + 1, end, L, R);
    }

    static void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = arr[start] % 2;
            return;
        }

        int mid = (start + end) / 2;
        int leftNode = node * 2;
        int rightNode = node * 2 + 1;
        build(leftNode, start, mid);
        build(rightNode, mid + 1, end);

        tree[node] = tree[leftNode] + tree[rightNode];
    }
}
