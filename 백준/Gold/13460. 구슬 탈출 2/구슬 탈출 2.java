import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        int[] pos = new int[4];

        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                board[i][j] = line[j];
                if (board[i][j] == 'R') {
                    pos[0] = i;
                    pos[1] = j;
                    board[i][j] = '.';
                }
                if (board[i][j] == 'B') {
                    pos[2] = i;
                    pos[3] = j;
                    board[i][j] = '.';
                }
            }
        }

        visited = new boolean[N][M][N][M];
        int answer = simulate(pos);
        System.out.println(answer);
    }

    static int simulate(int[] pos) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[]{pos[0], pos[1], pos[2], pos[3], 0});
        visited[pos[0]][pos[1]][pos[2]][pos[3]] = true;

        while (!q.isEmpty()) {
            int[] curr = q.pollFirst();
            int depth = curr[4];

            if (depth > 10) {
                return -1;
            }
            if (board[curr[0]][curr[1]] == 'O') {
                return depth;
            }

            for (int[] dir : dirs) {
                int[] next = roll(curr, dir, depth);
                if (next != null && !visited[next[0]][next[1]][next[2]][next[3]]) {
                    visited[next[0]][next[1]][next[2]][next[3]] = true;
                    q.offer(next);
                }
            }
        }
        return -1;
    }

    static int[] move(int r, int c, int dr, int dc) {
        int cnt = 0;
        while (true) {
            if (board[r+dr][c+dc] == '#') break;
            r += dr; c += dc; cnt++;
            if (board[r][c] == 'O') break;
        }
        return new int[]{r, c, cnt};
    }

    static int[] roll(int[] state, int[] dir, int depth) {
        int dr = dir[0], dc = dir[1];

        int[] rRes = move(state[0], state[1], dr, dc);
        int[] bRes = move(state[2], state[3], dr, dc);

        if (board[bRes[0]][bRes[1]] == 'O') return null;
        if (board[rRes[0]][rRes[1]] == 'O') return new int[]{rRes[0], rRes[1], bRes[0], bRes[1], depth+1};

        if (rRes[0] == bRes[0] && rRes[1] == bRes[1]) {
            if (rRes[2] > bRes[2]) {
                rRes[0] -= dr; rRes[1] -= dc;
            } else {
                bRes[0] -= dr; bRes[1] -= dc;
            }
        }
        return new int[]{rRes[0], rRes[1], bRes[0], bRes[1], depth+1};
    }
}
