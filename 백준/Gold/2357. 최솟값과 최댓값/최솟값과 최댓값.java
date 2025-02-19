import java.io.*;
import java.util.*;

public class Main {
    static final int MIN = 0, MAX = 1;
    static int N, M;
    static int[][] inputs;
    static int[] arr;
    static int[][] segment;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        inputs = new int[M][];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            inputs[i] = new int[] {a, b};
        }
        String answer = solve();
        bw.write(answer);

        bw.close();
        br.close();
    }

    static void init() {
        segment = new int[4 * N][2];
        build(1, 1, N);
    }

    static void build(int node, int start, int end) {
        if (start == end) {
            segment[node][MIN] = arr[start];
            segment[node][MAX] = arr[start];
            return;
        }

        int mid = (start + end) / 2;
        int leftChild =  2 * node;
        int rightChild = 2 * node + 1;

        build(leftChild, start, mid);
        build(rightChild, mid + 1, end);

        segment[node][MIN] = Math.min(segment[leftChild][MIN], segment[rightChild][MIN]);
        segment[node][MAX] = Math.max(segment[leftChild][MAX], segment[rightChild][MAX]);
    }

    static int[] query(int node, int start, int end, int L, int R) {
        if (R < start || end < L) {
            return new int[] {1_000_000_000, 0};
        }

        if (L <= start && end <= R) {
            return segment[node];
        }

        int mid = (start + end) / 2;
        
        int leftChild = 2 * node;
        int[] left = query(leftChild, start, mid, L, R);

        int rightChild = 2 * node + 1;
        int[] right = query(rightChild, mid + 1, end, L, R);

        return new int[] {Math.min(left[MIN], right[MIN]), Math.max(left[MAX], right[MAX])};
    }

    static String solve() {
        StringBuilder answer = new StringBuilder();

        init();
        for (int[] input: inputs) {
            int[] query =  query(1, 1, N, input[0], input[1]);
            answer.append(query[0] + " " + query[1] + "\n");
        }

        return answer.toString();
    }
}