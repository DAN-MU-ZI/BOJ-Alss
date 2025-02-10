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
        int K = Integer.parseInt(st.nextToken());
        int[] line = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            line[i] = Integer.parseInt(st.nextToken());
        }
        int answer = Solution.solve(N, K, line);
        sb.append(answer);

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Solution {
        static int N, K;
        static int[] line;
        static int[][] grid;

        public static int solve(int N, int K, int[] line) {
            init(N, K, line);

            int cnt = 0;
            while(!isMeetCondition()) {
                cnt++;

                addFish();
                // print();
                rotate();
                // print();
                balancing();
                // print();
                flatten();
                // print();
                flip();
                // print();
                balancing();
                // print();
                flatten();
                // print();
            }

            return cnt;
        }

        static void init(int N, int K, int[] line) {
            Solution.N = N;
            Solution.K = K;
            Solution.line = line;
            grid = new int[N][N];
            for (int i = 0; i < N; i++) {
                grid[0][i] = line[i];
            }
        }

        static boolean isMeetCondition() {
            int min = Integer.MAX_VALUE , max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                min = Math.min(min, grid[0][i]);
                max = Math.max(max, grid[0][i]);
            }

            return (max - min) <= K;
        }

        static void addFish() {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                min = Math.min(min, grid[0][i]);
            }

            for (int i = 0; i < N; i++) {
                if (grid[0][i] == min) {
                    grid[0][i]++;
                }
            }
        }

        static void print() {
            System.out.printf("padding: %d, rows: %d, cols: %d\n", padding, rows, cols);
            for (int r = 0; r < N; r++) {
                System.out.println(Arrays.toString(grid[r]));
            }
            System.out.println();
        }
        
        static int padding, rows, cols;
        static void rotate() {
            initIndex();

            while (padding + rows + cols - 1 < N) {
                // System.out.printf("padding: %d, rows: %d, cols: %d\n", padding, rows, cols);
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < cols; c++) {
                        grid[cols - c][padding + cols + r] = grid[r][padding + c];
                        grid[r][padding + c] = 0;
                    }
                }
                padding += Math.min(rows, cols);
                
                if (rows <= cols){
                    rows++;
                }else {
                    cols++;
                }
            }
        }

        /**
         * 모든 인접한 두 어항에 대해서, 물고기 수의 차이를 구한다. 
         * 이 차이를 5로 나눈 몫을 d라고 하자. 
         * d가 0보다 크면, 두 어항 중 물고기의 수가 많은 곳에 있는 
         * 물고기 d 마리를 적은 곳에 있는 곳으로 보낸다. 
         * 이 과정은 모든 인접한 칸에 대해서 동시에 발생한다. 
         */
        static int[][] deltas = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        static void balancing() {
            int[][] newGrid = new int[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (grid[r][c] == 0) continue;

                    for (int[] delta: deltas) {
                        int nr = r + delta[0];
                        int nc = c + delta[1];

                        if (!isValid(nr, nc) || grid[nr][nc] == 0) continue;

                        int d = (grid[r][c] - grid[nr][nc]) / 5;
                        if (d > 0) {
                            newGrid[r][c] -= d;
                            newGrid[nr][nc] += d;
                        }
                    }
                }
            }

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    grid[r][c] += newGrid[r][c];
                }
            }
        }

        static boolean isValid(int r, int c) {
            return 0 <= r && r < N && 0 <= c && c < N;
        }
        static void flatten() {
            int idx = 0;
            for (int c = 0; c < cols; c++){
                for (int r = 0; r < rows; r++) {
                    grid[0][idx++] = grid[r][padding + c];
                    grid[r][padding + c] = 0;
                }
            }
            initIndex();
        }
        static void flip() {
            padding = 0;
            rows = 1;
            cols = N / 2;

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    grid[rows * 2 - r - 1][padding + cols * 2 - 1 - c] = grid[r][padding + c];
                    grid[r][padding + c] = 0;
                }
            }

            padding = N / 2;
            rows = 2;
            cols = padding / 2;

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    grid[rows * 2 - r - 1][padding + cols * 2 - 1 - c] = grid[r][padding + c];
                    grid[r][padding + c] = 0;
                }
            }


            padding += padding / 2;
            rows *= 2;
            cols = N / 4;
        }

        static void initIndex() {
            padding = 0; rows = 1; cols = 1;
        }
    }
}