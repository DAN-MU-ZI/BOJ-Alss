import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Solution.solve(N, M, D, grid);
        sb.append(answer);
        
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Solution {
        static int N, M, D, cur, answer;
        static int[] pos;
        static int[][] grid;

        public static int solve(int N, int M, int D, int[][] grid) {
            init(N, M, D, grid);

            while (cur < 1 << M) {
                createNextPos();
                simulate();
            }

            return answer;
        }

        static int remain, result;
        static int[][] newGrid;
        static void simulate() {
            initSimulate();

            while (remain > 0) {
                shooting();
                down();
            }

            answer = Math.max(answer, result);
        }

        static void down() {
            for (int j = 0; j < M; j++) {
                if(newGrid[N - 1][j] == 1) {
                    remain--;
                }
            }
            for (int i = N - 1; i > 0; i--) {
                for (int j = 0; j < M; j++) {
                    newGrid[i][j] = newGrid[i - 1][j];
                }
            }
            for (int j = 0; j < M; j++) {
                newGrid[0][j] = 0;
            }
        }

        static void shooting() {
            boolean[][] isDead = new boolean[N][M];
            for (int i = 0; i < 3; i++) {
                bfs(isDead, pos[i]);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(isDead[i][j]) {
                        result++;
                        newGrid[i][j] = 0;
                        remain--;
                    }
                }
            }
        }

        static int[][] deltas = {{0, -1}, {1, 0}, {-1, 0}, {0, 1}};
        static void bfs(boolean[][] isDead, int i) {
            boolean[][] visited = new boolean[N][M];
            Queue<int[]> dq = new ArrayDeque<>();

            dq.add(new int[]{N, i, 0});

            while (!dq.isEmpty()) {
                int[] curPos = dq.poll();
                int r = curPos[0];
                int c = curPos[1];
                int d = curPos[2];

                if (isValid(r, c) && newGrid[r][c] == 1) {
                    isDead[r][c] = true;
                    return;
                }

                for (int[] delta: deltas) {
                    int nr = r + delta[0];
                    int nc = c + delta[1];
                    
                    if (!isValid(nr, nc) || visited[nr][nc] || d >= D) continue;
                    
                    visited[nr][nc] = true;
                    dq.add(new int[] {nr, nc, d + 1});
                }
            }
        }

        static boolean isValid(int r, int c) {
            return 0 <= r && r < N && 0 <= c && c < M;
        }

        static void initSimulate() {
            result = 0;
            remain = 0;
            newGrid = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    newGrid[i][j] = grid[i][j];
                    if (grid[i][j] == 1){
                        remain++;
                    }
                }
            }
        }

        static void createNextPos() {
            int c = cur + 1;
            for (; c < (1 << M); c++) {
                if (Integer.bitCount(c) == 3) {
                    int idx = 0;
                    for (int i = 0; i < M; i++) {
                        if ((c & (1 << i)) > 0) {
                            pos[idx++] = i;
                        }
                    }
                    break;
                }
            }
            cur = c;
        }

        static void init(int N, int M, int D, int[][] grid) {
            Solution.N = N;
            Solution.M = M;
            Solution.D = D;
            Solution.grid = grid;

            pos = new int[3];
            answer = 0;
        }
    }
}

