import java.io.*;
import java.util.*;

interface SegmentTree {
    void update(int index, long value);
    long query(int left, int right);
}

class FenwickTree implements SegmentTree {
    private final long[] tree;
    private final long[] arr;
    private final int n;

    public FenwickTree(int _n) {
        n = _n;
        tree = new long[n + 1];
        arr = new long[n + 1];
    }

    private int lsb(int x) {
        return x & -x;
    }

    private void add(int index, long delta) {
        while (index <= n) {
            tree[index] += delta;
            index += lsb(index);
        }
    }

    public void update(int index, long value) {
        add(index, value);
    }

    private long prefixSum(int index) {
        long sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= lsb(index);
        }
        return sum;
    }

    public long query(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }
}

public class Main {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        SegmentTree tree = new FenwickTree(N);
        while (Q-- > 0) {
            st = new  StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            if (i == 1) {
                tree.update(j, k);
            } else if (i == 2) {
                sb.append(tree.query(j, k)).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
