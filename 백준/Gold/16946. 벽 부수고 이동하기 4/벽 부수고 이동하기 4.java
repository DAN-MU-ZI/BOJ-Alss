import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] grid;
    static int[][] answer;
    static Group[][] map;

    static class Group implements Comparable<Group> {
        int id, value;

        public Group(int _id) {
            id = _id;
        }

        public int compareTo(Group g) {
            return id - g.id;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new boolean[N][M];
        answer = new int[N][M];
        map = new Group[N][M];
        for (int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line[j] == '1';
            }
        }

        boolean[][] visited = new boolean[N][M];
        int id = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!grid[i][j] && !visited[i][j]) {
                    bfs(id++, i, j, visited);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j]) {
                    Set<Integer> set = new HashSet<>();
                    int tot = 1;
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if (isValid(nr, nc) && !grid[nr][nc]) {
                            Group group = map[nr][nc];

                            if (set.contains(map[nr][nc].id)) {
                                continue;
                            }

                            set.add(group.id);
                            tot += group.value;
                        }
                    }
                    answer[i][j] = tot % 10;
                }
            }
        }

        for (int[] line : answer) {
            for (int num : line) {
                sb.append(num);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int[] dr = { 1, 0, -1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    static void bfs(int id, int i, int j, boolean[][] visited) {
        Deque<int[]> dq = new ArrayDeque<>();
        int cnt = 0;
        map[i][j] = new Group(id);

        dq.add(new int[] { i, j });
        visited[i][j] = true;
        cnt++;

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int r = cur[0];
            int c = cur[1];

            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];

                if (isValid(nr, nc) && !grid[nr][nc] && !visited[nr][nc]) {
                    cnt++;
                    visited[nr][nc] = true;
                    map[nr][nc] = map[i][j];
                    dq.add(new int[] { nr, nc });
                }
            }
        }

        map[i][j].value = cnt;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}