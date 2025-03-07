import java.io.*;
import java.util.*;

public class Main {
    static final int WALL = 1;
    static final int INF = Integer.MAX_VALUE;
    static int N, M, fuel;
    static int[][] grid;

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

            passengers[i] = new Passenger(i, srcX, srcY, dstX, dstY);
        }

        bw.write(String.valueOf(solve()));
        bw.close();
        br.close();
    }

    static int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    static class Passenger {
        int id, srcX, srcY, dstX, dstY, cost;

        public Passenger(int _id, int _srcX, int _srcY, int _dstX, int _dstY) {
            id = _id;
            srcX = _srcX;
            srcY = _srcY;
            dstX = _dstX;
            dstY = _dstY;
            cost = INF;
        }
    }

    static boolean isValid(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    static int getDistance(int srcX, int srcY, int dstX, int dstY) {
        boolean[][] visited = new boolean[N][N];
        Queue<int[]> dq = new ArrayDeque<>();

        visited[srcY][srcX] = true;

        dq.add(new int[] { srcY, srcX, 0 });
        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int y = cur[0];
            int x = cur[1];
            int distance = cur[2];

            if (x == dstX && y == dstY) {
                return distance;
            }

            for (int[] delta : deltas) {
                int ny = y + delta[1];
                int nx = x + delta[0];

                if (isValid(ny, nx) && !visited[ny][nx] && grid[ny][nx] != WALL) {
                    visited[ny][nx] = true;
                    dq.add(new int[] { ny, nx, distance + 1 });
                }
            }
        }

        return INF;
    }

    static Passenger[] passengers;
    static boolean[] isComplete;

    static int taxiY, taxiX;
    static Comparator<Passenger> comp = (s1, s2) -> {
        if (s1.cost == s2.cost) {
            if (s1.srcY == s2.srcY) {
                return s1.srcX - s2.srcX;
            } else {
                return s1.srcY - s2.srcY;
            }
        }
        return s1.cost - s2.cost;
    };

    static Passenger findNearestPassenger() {
        for (int i = 0; i < M; i++) {
            Passenger passenger = passengers[i];
            if (isComplete[i]) {
                passenger.cost = INF;
            } else {
                passenger.cost = getDistance(taxiX, taxiY, passenger.srcX, passenger.srcY);
            }
        }

        Passenger passenger = passengers[0];
        for (int i = 1; i < M; i++) {
            if (comp.compare(passenger, passengers[i]) > 0) {
                passenger = passengers[i];
            }
        }
        if (fuel > passenger.cost) {
            return passenger;
        }
        return null;
    }

    static int completeCount;

    static int solve() {
        completeCount = 0;
        while (completeCount < M) {
            int tmp = fuel;
            Passenger passenger = findNearestPassenger();
            if (passenger == null) {
                return -1;
            }
            tmp -= passenger.cost;

            int cost = getDistance(passenger.srcX, passenger.srcY, passenger.dstX, passenger.dstY);
            if (tmp >= cost) {
                taxiX = passenger.dstX;
                taxiY = passenger.dstY;
                tmp -= cost;
                fuel = tmp + cost * 2;
                isComplete[passenger.id] = true;
                completeCount++;
            } else {
                return -1;
            }
        }
        return fuel;
    }
}
