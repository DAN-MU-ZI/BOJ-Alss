import java.io.*;
import java.util.*;

class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        int[][]board = new int[N][M];
        for(int i = 0; i < N; i++){
            char[] line = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                board[i][j] = line[j] - '0';
            }
        }
        Solution solution = new Solution(M, N, board);
        int result = solution.solve();
        sb.append(result);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
    
    private static class Node {
        int r;
        int c;
        int cnt;

        public Node(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

	private static class Solution {
        private final int M;
        private final int N;
        private final int[][] board;
        private final int[] dr = {1, 0, -1, 0};
        private final int[] dc = {0, 1, 0, -1};


        public Solution(int M, int N, int[][] board) {
            this.M = M;
            this.N = N;
            this.board = board;
        }

        public int solve() {
            int[][] nodes = new int[N][M];
            for (int r = 0; r < N; r++) {
                Arrays.fill(nodes[r], INF);
            }

            Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cnt));
            pq.add(new Node(0, 0, 0));
            nodes[0][0] = 0;

            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                if (cur.cnt > nodes[cur.r][cur.c]) continue;

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];
                    int nCnt = cur.cnt + 1;

                    if (0 <= nr && nr < N && 0 <= nc && nc < M) {
                        if (board[nr][nc] == 1 && nCnt < nodes[nr][nc]) {
                            nodes[nr][nc] = nCnt;
                            pq.add(new Node(nr, nc, nCnt));
                        }
                        if (board[nr][nc] == 0 && cur.cnt < nodes[nr][nc]) {
                            nodes[nr][nc] = cur.cnt;
                            pq.add(new Node(nr, nc, cur.cnt));
                        }
                    }
                }                
            }

            return nodes[N - 1][M - 1];
        }
    }
}