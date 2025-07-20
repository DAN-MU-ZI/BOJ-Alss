import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static int[][] fire;
    static boolean[][] visited;
    static int R, C;

    /*
    지훈이와 불이 있다.
    지훈이는 불을 피해서 탈출해야 한다.
    탈출지점은 미로의 가장자리이다.
    불은 턴마다 상하좌우로 확산된다.
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fire = new int[R][C];
        visited = new boolean[R][C];

        Queue<int[]> firePositions = new ArrayDeque<>();
        int[] manPos = new int[2];

        for (int i = 0; i < R; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                map[i][j] = line[j];
                if (map[i][j] == 'F') {
                    firePositions.offer(new int[]{i, j});
                } else if (map[i][j] == 'J') {
                    manPos[0] = i;
                    manPos[1] = j;
                }
            }
        }

        simulateFire(firePositions);

        int result = simulateMap(manPos);
        System.out.println(result==-1 ? "IMPOSSIBLE" : result);
    }

    static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

    static int simulateMap(int[] pos) {
        Queue<int[]> q = new ArrayDeque<>();

        visited[pos[0]][pos[1]] = true;
        q.offer(new int[]{pos[0], pos[1], 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int turn = cur[2];

            // 탈출 조건
            if ( r==0 || r==R - 1 || c==0 || c==C - 1) {
                return turn + 1;
            }

            for (int[] dir: dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                int nt = turn + 1;

                if (
                    0<= nr && nr < R
                    && 0 <= nc && nc < C
                    && !visited[nr][nc]
                    && map[nr][nc] != '#'
                    && nt < fire[nr][nc]
                ) {
                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc, nt});
                }
            }
        }

        return -1;
    }

    static void simulateFire(Queue<int[]> positions) {
        // 불의 처음위치로부터 BFS를 통해서 각 좌표에 불이 언제 도달하는지 확인하자.
        for (int[] line: fire) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }

        Queue<int[]> q = new ArrayDeque<>();

        while (!positions.isEmpty()) {
            int[] pos = positions.poll();

            fire[pos[0]][pos[1]] = 0;
            q.offer(new int[]{pos[0], pos[1], 0});
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int turn = cur[2];

            if (fire[r][c] > turn) {
                continue;
            }

            for(int[] dir: dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                int nt = turn + 1;

                if (
                    0<= nr && nr < R
                    && 0 <= nc && nc < C
                    && map[nr][nc] != '#'
                    && nt < fire[nr][nc]
                ) {
                    fire[nr][nc] = nt;
                    q.offer(new int[]{nr, nc, nt});
                }
            }
        }
    }
}
