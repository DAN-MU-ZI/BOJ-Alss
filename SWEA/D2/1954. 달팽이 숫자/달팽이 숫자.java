import java.io.*;
import java.util.*;

public class Solution {
    static int N, M;
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            
            String answer = solve();
            sb.append(String.format("#%d\n%s", t, answer));
        }
        
        bw.write(sb.toString());

        bw.close();
        br.close();
    }

    static String solve() {
        grid = new int[N][N];
        StringBuilder answer = new StringBuilder();

        int r = 0, c = -1, d = 0, num = 0;
        int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while(true) {
            int[] delta = deltas[d];
            int dr = delta[0], dc = delta[1];
            int nr = r + dr, nc = c + dc;

            if (!isValid(nr, nc) || grid[nr][nc] != 0) { 
                d = (d + 1) % 4;
            }

            delta = deltas[d];
            dr = delta[0];
            dc = delta[1];
            nr = r + dr;
            nc = c + dc;

            if (!isValid(nr, nc) || grid[nr][nc] != 0) {
                break;
            }
            grid[nr][nc] = ++num;
            r = nr;
            c = nc;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer.append(grid[i][j] + " ");
            }
            answer.append("\n");
        }
        return answer.toString();
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}