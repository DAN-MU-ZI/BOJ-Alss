//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class Main {
//    private static int[] remain = {0, 5, 5, 5, 5, 5};
//
//    private static int answer = Integer.MAX_VALUE;
//    private static int[][] board;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        board = new int[10][10];
//        StringTokenizer st;
//        for (int i = 0; i < 10; i++) {
//            st = new StringTokenizer(br.readLine());
//            for (int j = 0; j < 10; j++) {
//                board[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        int sum = 0;
//        for (int i = 0; i < 10; i++) {
//            for (int j = 0; j < 10; j++) {
//                if (board[i][j] == 1)
//                    sum++;
//            }
//        }
//
//
//        dfs(0, 0, sum, 0);
//
//        if (answer == Integer.MAX_VALUE) {
//            answer = -1;
//        }
//        System.out.println(answer);
//    }
//
//    public static boolean isAttachable(int x, int y, int size) {
//        for (int i = x; i < x + size; i++) {
//            for (int j = y; j < y + size; j++) {
//                if (i < 0 || i >= 10 || j < 0 || j >= 10) {
//                    return false;
//                }
//
//                if (board[i][j] != 1) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
//
//    public static void fillBoard(int x, int y, int size, int value) {
//        for (int i = x; i < x + size; i++) {
//            for (int j = y; j < y + size; j++) {
//                board[i][j] = value;
//            }
//        }
//    }
//
//    public static void dfs(int x, int y, int sum, int cnt) {
////        if (sum == 0) {
////            answer = Math.min(answer, cnt);
////            return;
////        }
//        if (x == 9 && y == 9) {
//            answer = Math.min(answer, cnt);
//            return;
//        }
//
//        if (answer <= cnt)
//            return;
//
//        if (y > 9) {
//            dfs(x + 1, 0, sum, cnt);
//            return;
//        }
//
//        if (board[x][y] == 1) {
//            for (int i = 5; i >= 1; i--) {
//                if (remain[i] > 0 && isAttachable(x, y, i)) {
//                    fillBoard(x, y, i, 0);
//                    remain[i]--;
//                    dfs(x, y + 1, (int) (sum - Math.pow(i, 2)), cnt + 1);
//                    fillBoard(x, y, i, 1);
//                    remain[i]++;
//                }
//            }
//        } else {
//            dfs(x, y + 1, sum, cnt);
//        }
//    }
//
//}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int[] paper = {0, 5, 5, 5, 5, 5};
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        map = new int[10][10];
        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0, 0);

        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }

        bw.write(ans + "\n");
        bw.close();
        br.close();
    }

    // DFS + 백트래킹
    public static void DFS(int x, int y, int cnt) {
        // 맨 끝점에 도달하였을 경우, ans와 cnt 비교하고 종료.
        if (x >= 9 && y > 9) {
            ans = Math.min(ans, cnt);
            return;
        }

        // 최솟값을 구해야하는데 ans보다 cnt가 커지는 순간
        // 더 이상 탐색할 필요가 없어짐.
        if (ans <= cnt) {
            return;
        }

        // 아래 줄로 이동.
        if (y > 9) {
            DFS(x + 1, 0, cnt);
            return;
        }

        if (map[x][y] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (paper[i] > 0 && isAttach(x, y, i)) {
                    attach(x, y, i, 0); // 색종이를 붙임.
                    paper[i]--;
                    DFS(x, y + 1, cnt + 1);
                    attach(x, y, i, 1); // 색종이를 다시 뗌.
                    paper[i]++;
                }
            }
        } else { // 오른쪽으로 이동.
            DFS(x, y + 1, cnt);
        }
    }

    // 색종이를 붙이는 함수.
    public static void attach(int x, int y, int size, int state) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = state;
            }
        }
    }

    // 색종이를 붙일 수 있는지 확인.
    public static boolean isAttach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                    return false;
                }

                if (map[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
