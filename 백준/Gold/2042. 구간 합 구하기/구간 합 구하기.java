import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static long[][] inputs;
    static long[] arr, segment;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        inputs = new long[M + K][];
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            inputs[i] = new long[] {a, b, c};
        }
        String answer = solve();
        bw.write(answer);

        bw.close();
        br.close();
    }

    static void build(int node, int start, int end) {
        if (start == end) {
            segment[node] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        int leftChild = node * 2;
        int rightChild = node * 2 + 1;

        build(leftChild, start, mid);
        build(rightChild, mid + 1, end);
        
        segment[node] = segment[leftChild] + segment[rightChild];
    }

    static void update(int node, int start, int end, int idx, long val) {
        if (idx < start || end < idx) {
            return;
        }
        if (start == end) {
            segment[node] = val;
            return;
        }

        int mid = (start + end) / 2;
        int leftChild = node * 2;
        int rightChild = node * 2 + 1;

        if (idx <= mid) {
            update(leftChild, start, mid, idx, val);
        } else {
            update(rightChild, mid + 1, end, idx, val);
        }

        segment[node] = segment[leftChild] + segment[rightChild];
    }
    
    static long query(int node, int start, int end, int L, int R) {
        if (R < start || end < L) {
            return 0;
        }

        if (L <= start && end <= R) {
            return segment[node];
        }

        int mid = (start + end) / 2;
        int leftChild = node * 2;
        int rightChild = node * 2 + 1;

        long leftSum = query(leftChild, start, mid, L, R);
        long rightSum = query(rightChild, mid + 1, end, L, R);

        return leftSum + rightSum;
    }
    
    static void init() {
        segment = new long[4 * N];
        build(1, 1, N);
    }

    static String solve() {
        init();

        StringBuilder answer = new StringBuilder();
        
        for (int i = 0; i < M + K; i++) {
            long[] input = inputs[i];
            int a = (int)input[0];
            int b = (int)input[1];
            long c = input[2];

            if (a == 1) {
                update(1, 1, N, b, c);
            } else if (a == 2) {
                answer.append(query(1, 1, N, b, (int)c) + "\n");
            }
        }

        return answer.toString();
    }
}