import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int maxBlock = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findMax(board);
        dfs(0, board);

        System.out.println(maxBlock);
    }

    static void dfs(int depth, int[][] currentBoard) {
        if (depth == 5) {
            findMax(currentBoard);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[][] nextBoard = new int[N][N];
            for (int r = 0; r < N; r++) {
                nextBoard[r] = currentBoard[r].clone();
            }

            move(i, nextBoard);

            findMax(nextBoard);
            dfs(depth + 1, nextBoard);
        }
    }

    static void findMax(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxBlock = Math.max(maxBlock, board[i][j]);
            }
        }
    }

    static void move(int dir, int[][] board) {
        if (dir == 0) {
            for (int j = 0; j < N; j++) {
                Queue<Integer> q = new LinkedList<>();
                for (int i = 0; i < N; i++) {
                    if (board[i][j] != 0) q.offer(board[i][j]);
                    board[i][j] = 0;
                }
                compressAndFill(q, board, 0, j, 1, 0);
            }
        } else if (dir == 1) {
            for (int j = 0; j < N; j++) {
                Queue<Integer> q = new LinkedList<>();
                for (int i = N - 1; i >= 0; i--) {
                    if (board[i][j] != 0) q.offer(board[i][j]);
                    board[i][j] = 0;
                }
                compressAndFill(q, board, N - 1, j, -1, 0);
            }
        } else if (dir == 2) {
            for (int i = 0; i < N; i++) {
                Queue<Integer> q = new LinkedList<>();
                for (int j = 0; j < N; j++) {
                    if (board[i][j] != 0) q.offer(board[i][j]);
                    board[i][j] = 0;
                }
                compressAndFill(q, board, i, 0, 0, 1);
            }
        } else if (dir == 3) {
            for (int i = 0; i < N; i++) {
                Queue<Integer> q = new LinkedList<>();
                for (int j = N - 1; j >= 0; j--) {
                    if (board[i][j] != 0) q.offer(board[i][j]);
                    board[i][j] = 0;
                }
                compressAndFill(q, board, i, N - 1, 0, -1);
            }
        }
    }

    static void compressAndFill(Queue<Integer> q, int[][] board, int r, int c, int dr, int dc) {
        int prev = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            if (prev == 0) {
                prev = cur;
            } else {
                if (prev == cur) {
                    board[r][c] = prev * 2;
                    prev = 0;
                    r += dr;
                    c += dc;
                } else {
                    board[r][c] = prev;
                    prev = cur;
                    r += dr;
                    c += dc;
                }
            }
        }

        if (prev != 0) {
            board[r][c] = prev;
        }
    }
}