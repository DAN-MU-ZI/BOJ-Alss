import java.io.*;
import java.util.*;

class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
		StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] info = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken()) - 1; // 0-based
            info[i][1] = Integer.parseInt(st.nextToken());
        }
        Solution solution = new Solution(N, M, A, info);
        sb.append(solution.solve());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

    static class Solution {
        int[] dr = { 0,-1,-1,-1, 0, 1, 1, 1};
        int[] dc = {-1,-1, 0, 1, 1, 1, 0,-1};

        int[] cdr = { 1,-1,-1, 1};
        int[] cdc = { 1,-1, 1,-1};

        int N, M;
        int[][] A, info;
        boolean[][] cloud;

        public Solution(int N, int M, int[][] A, int[][] info) {
            this.N = N;
            this.M = M;
            this.A = A;
            this.info = info;
        }

        private boolean[][] createCloudBluePrint() {
            return new boolean[N][N];
        }

        private void initCloud() {
            cloud = createCloudBluePrint();
            cloud[N-1][0] = true;
            cloud[N-1][1] = true;
            cloud[N-2][0] = true;
            cloud[N-2][1] = true;
        }

        private void moveCloud(int d, int s) {
            boolean[][] newCloud = createCloudBluePrint();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (cloud[r][c]) {
                        int nr = (r + (s % N) * dr[d] + N) % N;
                        int nc = (c + (s % N) * dc[d] + N) % N;
                        newCloud[nr][nc] = true;
                    }
                }
            }
            cloud = newCloud;
        }

        private void rain() {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (cloud[r][c]) {
                        A[r][c] += 1;
                    }
                }
            }
        }

        private boolean[][] flipCloud() {
            return null;
        }

        private boolean isValid(int r, int c) {
            return 0 <= r && r < N && 0 <= c && c < N;
        }
        private void copyWater() {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (cloud[r][c]) {
                        int isWaterCount = 0;
                        for (int i = 0; i < 4; i++) {
                            int nr = r + cdr[i];
                            int nc = c + cdc[i];

                            if (isValid(nr, nc) && A[nr][nc] > 0) {
                                isWaterCount++;
                            }
                        }
                        A[r][c] += isWaterCount;
                    }
                }
            }
        }
        private void makeCloud() {
            boolean[][] newCloud = createCloudBluePrint();

            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (!cloud[r][c] && A[r][c] >= 2) {
                        newCloud[r][c] = true;
                        A[r][c] -= 2;
                    }
                }
            }

            cloud = newCloud;
        }
        private int summation() {
            int sum = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    sum += A[r][c];
                }
            }
            return sum;
        }

        public int solve() {
            initCloud();

            for (int[] command: info) {
                int d = command[0];
                int s = command[1];

                // 1. 모든 구름이 di 방향으로 si칸 이동한다.
                moveCloud(d, s);

                // 2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
                rain();

                // 3. 구름이 모두 사라진다. (보류)
                flipCloud();

                // 4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
                // 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
                // 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
                copyWater();

                // 5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
                makeCloud();
            }

            // 6. M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.
            int answer = summation(); 

            return answer;
        }
    }
}
