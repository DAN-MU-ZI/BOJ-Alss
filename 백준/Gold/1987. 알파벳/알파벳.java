import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int[][] direct = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
    };

    static int R;
    static int C;
    static char[][] board;
    static boolean[] visited = new boolean[26];
    static int answer = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        visited[board[0][0] - 'A'] = true;
        dfs(0, 0, 1);

        System.out.println(answer);
    }

    public static boolean isOutOfBound(int r, int c) {
        return !(0 <= r && r < R && 0 <= c && c < C);
    }

    public static void dfs(int r, int c, int stk) {
        answer = Math.max(answer, stk);

        for (int i = 0; i < 4; i++) {
            int dy = direct[i][1];
            int dx = direct[i][0];

            int nx = r + dx;
            int ny = c + dy;

            if (isOutOfBound(nx, ny))
                continue;


            char nextChar = board[nx][ny];
            if (visited[nextChar - 'A'])
                continue;

            visited[nextChar - 'A'] = true;

            dfs(nx, ny, stk + 1);

            visited[nextChar - 'A'] = false;
        }
    }
}