import java.util.*;
import java.io.*;

class Solution {
    static int N, M, C;
    static int[][] grid = new int[20][20];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    grid[i][j] = num;
                    if (num == 1) {
                        C++;
                    }
                }
            }

            int answer = solve();
            sb.append(String.format("#%d %d\n", test_case, answer));
        }
        System.out.println(sb);
    }

    static int solve() {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = calc(i, j);
                answer = Math.max(answer, cnt);
            }
        }
        return answer;
    }

    static int calc(int r, int c) {
        int max = 0;
        int K = 0;

        while (true) {
            K++;
            int cnt = 0;
            int cost = K * K + (K - 1) * (K - 1);

            for (int i = r - (K - 1); i <= r + (K - 1); i++) {
                for (int j = c - (K - 1); j <= c + (K - 1); j++) {
                    if (isValid(i, j) && getDistance(r, c, i, j) < K && grid[i][j] == 1) {
                        cnt++;
                    }
                }
            }

            if (cnt * M >= cost) {
                max = Math.max(max, cnt);
            }

            if (K > N + 1)
                break;
        }

        return max;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static int getDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}