import java.util.*;
import java.io.*;

class Solution {
    static int N, K, H;
    static int[][] grid = new int[8][8];
    static boolean[][] visited = new boolean[8][8];

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            H = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                    H = Math.max(H, grid[i][j]);
                }
            }
            int answer = solve();
            sb.append(String.format("#%d %d\n", test_case, answer));
        }
        System.out.println(sb);
    }

    static int[] dr = { 1, 0, -1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    static int solve() {
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == H) {
                    visited[i][j] = true;
                    answer = Math.max(answer, dfs(i, j, 1, true));
                    visited[i][j] = false;
                }
            }
        }
        return answer;
    }

    static int dfs(int r, int c, int length, boolean canDig) {
        int maxLen = length;
        int currentHeight = grid[r][c];

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isValid(nr, nc) && !visited[nr][nc]) {
                if (grid[nr][nc] < currentHeight) {
                    visited[nr][nc] = true;
                    maxLen = Math.max(maxLen, dfs(nr, nc, length + 1, canDig));
                    visited[nr][nc] = false;
                } else if (canDig) {
                    for (int d = 1; d <= K; d++) {
                        if (grid[nr][nc] - d < currentHeight) {
                            visited[nr][nc] = true;
                            grid[nr][nc] -= d;
                            maxLen = Math.max(maxLen, dfs(nr, nc, length + 1, false));
                            grid[nr][nc] += d;
                            visited[nr][nc] = false;
                        }
                    }
                }
            }
        }
        return maxLen;
    }

    static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
