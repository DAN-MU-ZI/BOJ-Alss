import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] grid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        grid = new char[R + 1][C + 1];
        for (int i = 0; i < R; i++) {
            char[] line = br.readLine().trim().toCharArray();
            for (int j = 0; j < C; j++) {
                if ('a' <= line[j] && line[j] <= 'z') {
                    grid[i][j] = line[j];
                }
            }
        }

        Queue<String> pq = new PriorityQueue<>();

        for (int r = 0; r < R; r++) {
            int c = 0;
            while (c + 1 < C) {
                if (grid[r][c] == 0 || grid[r][c+1] == 0) {
                    c++;
                    continue;
                } else {
                    int tmp = c + 2;
                    while (tmp <= C && grid[r][tmp] != 0) {
                        tmp++;
                    }
                    
                    char[] str = new char[tmp - c];
                    for (int i = c; i < tmp; i++) {
                        str[i - c] = grid[r][i];
                    }
                    pq.add(String.valueOf(str));
                    c = tmp;
                }
            }
        }

        for (int c = 0; c < C; c++) {
            int r = 0;
            while (r + 1 < R) {
                if (grid[r][c] == 0 || grid[r + 1][c] == 0) {
                    r++;
                    continue;
                } else {
                    int tmp = r + 2;
                    while (tmp <= R && grid[tmp][c] != 0) {
                        tmp++;
                    }
                    
                    char[] str = new char[tmp - r];
                    for (int i = r; i < tmp; i++) {
                        str[i - r] = grid[i][c];
                    }
                    pq.add(String.valueOf(str));
                    r = tmp;
                }
            }
        }

        // for (String s: pq) {
        //     System.out.println(s);
        // }

        bw.write(pq.peek());
        bw.close();
        br.close();
    }
}