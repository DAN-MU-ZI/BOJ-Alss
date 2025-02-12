import java.io.*;
import java.util.*;

public class Solution {
    static Map<Integer, Integer> wormholeMap, wormholeInfo;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] reflect = {
        {},
        {1,3,0,2},
        {3,0,1,2},
        {2,0,3,1},
        {1,2,3,0},
        {1,0,3,2}
    };

    static int N;
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine().trim());
            grid = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int answer = solve();
            sb.append(String.format("#%d %d\n", t, answer));
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int solve() {
        searchWormhole();

        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int d = 0; d < 4; d++) {
                    int cell = grid[r][c];
                    if (cell != 0) continue;
                    int score = simulate(r, c, d);
                    answer = Math.max(answer, score);
                }
            }
        }
        return answer;
    }

    static int simulate(int r, int c, int d) {
        int score = 0;
        int nr = r;
        int nc = c;
        int nd = d;

        // System.out.printf("%d %d %d\n", nr, nc, nd);
        while (true) {
            nr += deltas[nd][0];
            nc += deltas[nd][1];
            if (nr == r && nc == c) return score;
            // System.out.printf("%d %d %d %d\n", nr, nc, nd, score);

            if (isValid(nr, nc)) {
                int cell = grid[nr][nc];
                if (1 <= cell && cell <= 5) {
                    nd = reflect[cell][nd];
                    score++;
                } else if (6 <= cell && cell <= 10) {
                    int warp = wormholeMap.get(nr * N + nc);
                    nr = warp / N;
                    nc = warp % N;
                } else if (cell == -1) {
                    return score;
                }
            } else {
                nd = reflect[5][nd];
                // nr += deltas[nd][0];
                // nc += deltas[nd][1];
                score++;
            }
        }
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static void searchWormhole() {
        wormholeMap = new HashMap();
        wormholeInfo = new HashMap();
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int cell = grid[r][c];
                if (cell >= 6) {
                    int pos = r * N + c;
                    Integer info = wormholeInfo.getOrDefault(cell, null);
                    if (info == null) {
                        wormholeInfo.put(cell, pos);
                    } else {
                        wormholeMap.put(pos, info);
                        wormholeMap.put(info, pos);
                    }                    
                }
            }
        }
        // System.out.println(wormholeMap);
        // System.out.println(wormholeInfo);
    }
}