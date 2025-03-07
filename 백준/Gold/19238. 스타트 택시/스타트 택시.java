import java.io.*;
import java.util.*;

public class Main {
    static final int WALL = 1;
    static final int INF = Integer.MAX_VALUE;
    static int N, M, fuel;
    static int[][] grid;

    static int taxiY, taxiX;

    static int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    static Passenger[] passengers;
    static boolean[] isComplete;

    static class Passenger {
        int id;
        int srcY, srcX, dstY, dstX;

        public Passenger(int id, int srcY, int srcX, int dstY, int dstX) {
            this.id = id;
            this.srcY = srcY;
            this.srcX = srcX;
            this.dstY = dstY;
            this.dstX = dstX;
        }
    }

    static boolean isValid(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    static int[][] getDistancesFrom(int startY, int startX) {
        int[][] distances = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(distances[i], -1);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        distances[startY][startX] = 0;
        queue.add(new int[] { startY, startX });

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0], x = cur[1];
            for (int[] d : deltas) {
                int ny = y + d[0];
                int nx = x + d[1];
                if (isValid(ny, nx) && grid[ny][nx] != WALL && distances[ny][nx] == -1) {
                    distances[ny][nx] = distances[y][x] + 1;
                    queue.add(new int[] { ny, nx });
                }
            }
        }
        return distances;
    }

    static Passenger findNearestPassenger() {
        int[][] distanceMap = getDistancesFrom(taxiY, taxiX);
        Passenger candidate = null;
        int minDistance = INF;
        for (int i = 0; i < M; i++) {
            if (isComplete[i])
                continue;
            Passenger p = passengers[i];
            int d = distanceMap[p.srcY][p.srcX];
            if (d == -1)
                continue;
            if (d < minDistance) {
                minDistance = d;
                candidate = p;
            } else if (d == minDistance) {
                if (p.srcY < candidate.srcY || (p.srcY == candidate.srcY && p.srcX < candidate.srcX)) {
                    candidate = p;
                }
            }
        }
        if (candidate != null && fuel >= minDistance) {
            return candidate;
        }
        return null;
    }

    static int solve() {
        int completeCount = 0;
        while (completeCount < M) {
            Passenger passenger = findNearestPassenger();
            if (passenger == null)
                return -1;

            int[][] distMapToPassenger = getDistancesFrom(taxiY, taxiX);
            int distanceToPassenger = distMapToPassenger[passenger.srcY][passenger.srcX];
            if (distanceToPassenger == -1 || fuel < distanceToPassenger)
                return -1;
            fuel -= distanceToPassenger;
            taxiY = passenger.srcY;
            taxiX = passenger.srcX;

            int[][] distMapToDestination = getDistancesFrom(taxiY, taxiX);
            int distanceToDestination = distMapToDestination[passenger.dstY][passenger.dstX];
            if (distanceToDestination == -1 || fuel < distanceToDestination)
                return -1;
            fuel -= distanceToDestination;
            fuel += distanceToDestination * 2;
            taxiY = passenger.dstY;
            taxiX = passenger.dstX;

            isComplete[passenger.id] = true;
            completeCount++;
        }
        return fuel;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxiY = Integer.parseInt(st.nextToken()) - 1;
        taxiX = Integer.parseInt(st.nextToken()) - 1;

        passengers = new Passenger[M];
        isComplete = new boolean[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int srcY = Integer.parseInt(st.nextToken()) - 1;
            int srcX = Integer.parseInt(st.nextToken()) - 1;
            int dstY = Integer.parseInt(st.nextToken()) - 1;
            int dstX = Integer.parseInt(st.nextToken()) - 1;
            passengers[i] = new Passenger(i, srcY, srcX, dstY, dstX);
        }

        bw.write(String.valueOf(solve()));
        bw.close();
        br.close();
    }
}
