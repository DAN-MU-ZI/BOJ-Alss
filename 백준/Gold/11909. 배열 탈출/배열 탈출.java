import java.io.*;
import java.util.*;

class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

        /**
         * cost : 돈, 거리
         */

        int n = Integer.parseInt(br.readLine());
        int[][] A = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Solution solution = new Solution(n, A);
        int answer = solution.solve();
        sb.append(answer);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
    
    private static class Node {
        int r;
        int c;
        int coin;
        int dist;

        public Node(int r, int c, int coin, int dist) {
            this.r = r;
            this.c = c;
            this.coin = coin;
            this.dist = dist;
        }
    }

	private static class Solution {
        private final int n;
        private final int[][] A;

        public Solution(int n, int[][] A) {
            this.n = n;
            this.A = A;
        }

        public int solve() {
            int answer = INF;
            int[][]board= new int[n][n];
            for (int i = 0; i < n; i++) {
                Arrays.fill(board[i], INF);
            }

            Queue<Node> pq = new PriorityQueue<>((s1, s2) -> {
                return s1.coin - s2.coin;
            });
            pq.add(new Node(0, 0, 0, 0));
            board[0][0] = 0;
            
            while (!pq.isEmpty()) {
                Node cur = pq.poll();
                int r = cur.r;
                int c = cur.c;
                int coin = cur.coin;
                int dist = cur.dist;

                if (coin > board[cur.r][cur.c]) continue;
                
                int nr, nc, nDist, demand;
                nDist = dist + 1;

                nr = r + 1;
                nc = c;
                if (isValid(nr, nc)) {
                    demand = Math.max(0, A[nr][nc] + 1 - A[r][c]);
                    if (coin + demand < board[nr][nc]) {
                        board[nr][nc] = coin + demand;
                        pq.add(new Node(nr, nc, coin + demand, nDist));
                    }
                }

                nr = r;
                nc = c + 1;
                if (isValid(nr, nc)) {
                    demand = Math.max(0, A[nr][nc] + 1 - A[r][c]);
                    if (coin + demand < board[nr][nc]) {
                        board[nr][nc] = coin + demand;
                        pq.add(new Node(nr, nc, coin + demand, nDist));
                    }
                }
            }

            // for (int i = 0; i < n; i++) {
            //     System.out.println(Arrays.toString(board[i]));
            // }
            return board[n - 1][n - 1];
        }

        private boolean isValid(int r, int c) {
            return 0 <= r && r < n && 0 <= c && c < n;
        }
    }       
}