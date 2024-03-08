import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static int[] count = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, n);
        for (int i : count) {
            System.out.println(i);
        }
    }

    private static void dfs(final int x, final int y, final int size) {
        int cur = arr[x][y];
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
            int cell_size = size / 3;

            if (cell_size == 1) {
                for (int i = x; i < x + size; i += cell_size) {
                    for (int j = y; j < y + size; j += cell_size) {
                        count[arr[i][j] + 1] += 1;
                    }
                }
            } else {
                for (int i = x; i < x + size; i += cell_size) {
                    for (int j = y; j < y + size; j += cell_size) {
                        dfs(i, j, cell_size);
                    }
                }
            }
        } else {
            count[cur + 1] += 1;
        }
    }
}