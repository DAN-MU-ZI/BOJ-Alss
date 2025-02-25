import java.io.*;
import java.util.*;

public class Solution {
    static int N, M;
    static int[] ops;
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        ops = new int[4];
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            M = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                ops[i] = Integer.parseInt(st.nextToken());
                M += ops[i];
            }

            nums = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            int answer = solve();
            sb.append(String.format("#%d %d\n", t, answer));
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int min, max;
    static int solve() {
        init();
        

        dfs(nums[0], 0);

        return Math.abs(max - min);
    }

    static void init() {
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
    }

    static void dfs(int acc, int stk) {
        if (stk == M) {
            min = Math.min(min, acc);
            max = Math.max(max, acc);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (ops[i] > 0) {
                ops[i]--;

                dfs(calc(i, acc, nums[stk + 1]), stk + 1);

                ops[i]++;
            }            
        }
    }

    static int calc (int op, int s1, int s2) {
        if (op == 0) {
            return s1 + s2;
        } else if (op == 1) {
            return s1 - s2;
        } else if (op == 2) {
            return s1 * s2;
        } else {
            return s1 / s2;
        }
    }
}