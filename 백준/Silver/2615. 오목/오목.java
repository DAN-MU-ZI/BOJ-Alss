import java.io.*;
import java.util.*;

public class Main {
    static int SIZE = 19;
    static int[][] map;

    static int[] dr = {1, 0, 1, -1};
    static int[] dc = {0, 1, 1, 1};

    public static void main(String[] args) throws Exception {
        //---------여기에 코드를 작성하세요.---------------//
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        map = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        
        int[] answer = solve();
        
        if (answer == null) {
            sb.append(0);
        } else {
            int r = answer[0];
            int c = answer[1];
            sb.append(String.format("%d\n%d %d", map[r][c], r + 1, c + 1));
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] solve() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (map[r][c] == 0) continue;
                
                for (int i = 0; i < 4; i++) {
                    if (serialCounter(r, c, i) == 5 && !backwardEquals(r, c, i)) {
                        return new int[] {r, c};
                    }
                }
            }
        }
        
        return null;
    }

    private static boolean backwardEquals(int r, int c, int d) {
        int nr = r - dr[d];
        int nc = c - dc[d];
        if (isValid(nr, nc)) {
            return map[nr][nc] == map[r][c];
        }

        return false;
    }

    private static int serialCounter(int r, int c, int d) {
        int cnt = 1;
        int nr = r;
        int nc = c;
        
        while(true) {
            nr += dr[d];
            nc += dc[d];
            
            if (isValid(nr, nc) && map[r][c] == map[nr][nc]) {
                cnt++;
            } else {
                break;
            }
        }
        
        // nr = r;
        // nc = c;
        // while(true) {
        //     nr -= dr[d];
        //     nc -= dc[d];

        //     if (isValid(nr, nc) && map[r][c] == map[nr][nc]) {
        //         cnt++;
        //     } else {
        //         break;
        //     }
        // }

//        System.out.printf("r: %d, c: %d, d: %d, cnt: %d\n", r, c, d, cnt);
        return cnt;
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < SIZE && 0 <= c && c < SIZE;
    }
}