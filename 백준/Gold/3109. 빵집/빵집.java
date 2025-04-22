import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static boolean[][] map = new boolean[10000][500];

    static public void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // map = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = line[j] == '.';
            }
        }

        int answer = solution();
        System.out.println(answer);
    }

    static int solution() {
        boolean[][] visited = new boolean[R][C];
        int answer = 0;
        for (int i = 0; i < R; i++) {
            if (simulate(i, visited)) {
                answer++;
            }
        }

        return answer;
    }

    static int[] dr = { -1, 0, 1 };

    static boolean simulate(int s, boolean[][] visited) {
        return dfs(s, 0, visited);
    }

    static boolean dfs(int r, int c, boolean[][] visited) {
        if (c == C - 2) {
            return true;
        }

        int nc = c + 1;
        for (int d : dr) {
            int nr = r + d;
            if (isValid(nr, nc) && map[nr][nc] && !visited[nr][nc]) {
                visited[nr][nc] = true;
                if (dfs(nr, nc, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
