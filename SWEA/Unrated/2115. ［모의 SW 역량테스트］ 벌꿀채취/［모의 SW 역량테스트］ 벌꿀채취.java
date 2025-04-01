import java.util.*;
import java.io.*;

class Solution {
    static int N, M, C;
    static int[][] grid = new int[10][10];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solve();

            sb.append(String.format("#%d %d\n", test_case, answer));
        }

        System.out.println(sb);
    }

    static int[][] compress = new int[10][10];
    static int answer;

    static void solve() {
        answer = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((s1, s2) -> {
            if (s1[2] == s2[2]) {
                if (s1[0] == s2[0]) {
                    return s1[1] - s2[1];
                }
                return s1[0] - s2[0];
            }
            return -(s1[2] - s2[2]);
        });
        for (int i = 0; i < N; i++) {
            int slide = 0;
            int tot = 0;
            for (int j = 0; j < M - 1; j++) {
                slide += grid[i][j];
                tot += grid[i][j] * grid[i][j];
            }
            int prev = 0;
            for (int j = M - 1; j < N; j++) {
                slide -= prev;
                tot -= prev * prev;

                slide += grid[i][j];
                tot += grid[i][j] * grid[i][j];

                if (slide <= C) {
                    pq.add(new int[] { i, j - M + 1, tot });
                    compress[i][j - M + 1] = tot;
                } else {
                    pq.add(new int[] { i, j - M + 1, getSubSum(j, grid[i]) });
                    compress[i][j - M + 1] = getSubSum(j, grid[i]);
                }

                prev = grid[i][j - M + 1];
            }
        }

        int[] best = pq.poll();
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if ((best[0] != cur[0]) || (best[1] + M <= cur[1])) {
                answer = Math.max(answer, best[2] + cur[2]);
            }
        }

        // for (int[] line : compress) {
        // System.out.println(Arrays.toString(line));
        // }
    }

    static int getSubSum(int end, int[] line) {
        int best = 0;

        for (int i = 0; i < (1 << M); i++) {
            int tmp = 0;
            int tot = 0;
            for (int j = 0; j < M; j++) {
                if ((i & (1 << j)) > 0) {
                    tmp += line[end - j];
                    tot += line[end - j] * line[end - j];
                }
            }
            if (tmp <= C && best < tot) {
                best = Math.max(best, tot);
            }
        }
        return best;
    }
}