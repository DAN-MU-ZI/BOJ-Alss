import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] remain = {0, 5, 5, 5, 5, 5};

    private static int answer = Integer.MAX_VALUE;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[10][10];
        StringTokenizer st;
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (board[i][j] == 1)
                    sum++;
            }
        }


        dfs(0, 0, sum, 0);

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        System.out.println(answer);
    }

    public static boolean isAttachable(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                    return false;
                }

                if (board[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void fillBoard(int x, int y, int size, int value) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                board[i][j] = value;
            }
        }
    }

    public static void dfs(int x, int y, int sum, int cnt) {
        if (sum == 0) {
            answer = Math.min(answer, cnt);
            return;
        }
        if (x >= 9 && y > 9) {
            answer = Math.min(answer, cnt);
            return;
        }

        if (answer <= cnt)
            return;

        if (y > 9) {
            dfs(x + 1, 0, sum, cnt);
            return;
        }

        if (board[x][y] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (remain[i] > 0 && isAttachable(x, y, i)) {
                    fillBoard(x, y, i, 0);
                    remain[i]--;
                    dfs(x, y + 1, (int) (sum - Math.pow(i, 2)), cnt + 1);
                    fillBoard(x, y, i, 1);
                    remain[i]++;
                }
            }
        } else {
            dfs(x, y + 1, sum, cnt);
        }
    }

}