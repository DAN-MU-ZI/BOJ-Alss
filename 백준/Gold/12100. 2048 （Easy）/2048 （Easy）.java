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
                System.arraycopy(currentBoard[r], 0, nextBoard[r], 0, N);
            }

            move(i, nextBoard);

            if (isSame(currentBoard, nextBoard)) continue;

            findMax(nextBoard);
            dfs(depth + 1, nextBoard);
        }
    }

    static boolean isSame(int[][] a, int[][] b) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }

    static void findMax(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] > maxBlock) maxBlock = board[i][j];
            }
        }
    }

    static void move(int dir, int[][] board) {
        if (dir == 0) {
            for (int j = 0; j < N; j++) {
                int index = 0;
                int prev = 0;
                for (int i = 0; i < N; i++) {
                    if (board[i][j] != 0) {
                        if (prev == board[i][j]) {
                            board[index - 1][j] *= 2;
                            prev = 0;
                        } else {
                            board[index][j] = board[i][j];
                            prev = board[i][j];
                            index++;
                        }
                    }
                }
                for (int i = index; i < N; i++) board[i][j] = 0;
            }
        } else if (dir == 1) {
            for (int j = 0; j < N; j++) {
                int index = N - 1;
                int prev = 0;
                for (int i = N - 1; i >= 0; i--) {
                    if (board[i][j] != 0) {
                        if (prev == board[i][j]) {
                            board[index + 1][j] *= 2;
                            prev = 0;
                        } else {
                            board[index][j] = board[i][j];
                            prev = board[i][j];
                            index--;
                        }
                    }
                }
                for (int i = index; i >= 0; i--) board[i][j] = 0;
            }
        } else if (dir == 2) {
            for (int i = 0; i < N; i++) {
                int index = 0;
                int prev = 0;
                for (int j = 0; j < N; j++) {
                    if (board[i][j] != 0) {
                        if (prev == board[i][j]) {
                            board[i][index - 1] *= 2;
                            prev = 0;
                        } else {
                            board[i][index] = board[i][j];
                            prev = board[i][j];
                            index++;
                        }
                    }
                }
                for (int j = index; j < N; j++) board[i][j] = 0;
            }
        } else if (dir == 3) {
            for (int i = 0; i < N; i++) {
                int index = N - 1;
                int prev = 0;
                for (int j = N - 1; j >= 0; j--) {
                    if (board[i][j] != 0) {
                        if (prev == board[i][j]) {
                            board[i][index + 1] *= 2;
                            prev = 0;
                        } else {
                            board[i][index] = board[i][j];
                            prev = board[i][j];
                            index--;
                        }
                    }
                }
                for (int j = index; j >= 0; j--) board[i][j] = 0;
            }
        }
    }
}