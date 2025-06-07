import java.io.*;
import java.util.*;

public class Main {
    static int N, Q;
    static int[] arr;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        tree = new ArrayList[4 * (N + 1)];
        for (int i = 0; i < tree.length; i++) tree[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) arr[i] = Integer.parseInt(st.nextToken());

        build(1, 1, N);

        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            sb.append(query(1, 1, N, l, r, k)).append('\n');
        }
        System.out.print(sb);
    }

    static void build(int node, int start, int end) {
        if (start == end) {
            tree[node].add(arr[start]);
            return;
        }
        int mid = (start + end) / 2;
        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> merged = new ArrayList<>(left.size() + right.size());
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) merged.add(left.get(i++));
            else merged.add(right.get(j++));
        }
        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));
        return merged;
    }

    static int query(int node, int start, int end, int l, int r, int k) {
        if (r < start || end < l) return 0; // 겹치지 않음
        if (l <= start && end <= r) {
            ArrayList<Integer> list = tree[node];
            int idx = upperBound(list, k);
            return list.size() - idx;
        }
        int mid = (start + end) / 2;
        return query(node * 2, start, mid, l, r, k)
             + query(node * 2 + 1, mid + 1, end, l, r, k);
    }

    static int upperBound(ArrayList<Integer> list, int k) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) <= k) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}
