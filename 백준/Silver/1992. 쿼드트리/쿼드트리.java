import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static char[][] arr;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        dfs(0, 0, n);
        System.out.println(answer);
    }

    private static void dfs(final int x, final int y, final int size) {
        char cur = arr[x][y];
        boolean is_break = false;
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != cur) {
                    is_break = true;
                    break;
                }
            }
            if (is_break) {
                break;
            }
        }

        if (is_break) {
            int quad_size = size / 2;

            if (quad_size == 1) {
                answer.append("(");
                for (int i = x; i < x + size; i += quad_size) {
                    for (int j = y; j < y + size; j += quad_size) {
                        answer.append(arr[i][j]);
                    }
                }
                answer.append(")");
                return;
            }

            answer.append("(");
            for (int i = x; i < x + size; i += quad_size) {
                for (int j = y; j < y + size; j += quad_size) {
                    dfs(i, j, quad_size);
                }
            }
            answer.append(")");
        } else {
            answer.append(cur);
        }
    }
}