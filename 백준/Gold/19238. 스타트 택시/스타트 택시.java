import java.io.*;
import java.util.*;

public class Main {
    static final int WALL = 1;
    static final int INF = Integer.MAX_VALUE;
    static int N, M, fuel;
    static int[][] grid;

    // 택시의 현재 위치 (행, 열)
    static int taxiY, taxiX;

    // 승객 정보 배열, isComplete 배열
    static Passenger[] passengers;
    static boolean[] isComplete;

    // 전체 셀 개수: N * N (격자는 0-index)
    static int totalCells;
    // precomputedDistances[srcIndex][dstIndex] : src에서 dst까지의 최단 거리, 도달 불가면 INF
    static int[][] distMatrix;

    // 상, 좌, 하, 우 이동 (행, 열 순서)
    static int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

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

    // 격자 내 좌표의 유효성 검사
    static boolean isValid(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    // (y, x)를 1차원 인덱스로 변환
    static int cellIndex(int y, int x) {
        return y * N + x;
    }

    // 전체 격자에 대해 precomputed distance matrix 생성 (각 빈칸에서 다른 빈칸까지)
    static void precomputeDistances() {
        totalCells = N * N;
        distMatrix = new int[totalCells][totalCells];
        // 초기화: 모든 값 INF
        for (int i = 0; i < totalCells; i++) {
            Arrays.fill(distMatrix[i], INF);
        }

        // 모든 셀에 대해 BFS 실행 (벽이면 건너뜀)
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int startIdx = cellIndex(y, x);
                if (grid[y][x] == WALL)
                    continue; // 벽인 경우, 출발점으로 사용할 수 없음.
                // BFS from (y, x)
                boolean[][] visited = new boolean[N][N];
                Queue<int[]> queue = new ArrayDeque<>();
                visited[y][x] = true;
                distMatrix[startIdx][startIdx] = 0;
                queue.add(new int[] { y, x, 0 });
                while (!queue.isEmpty()) {
                    int[] cur = queue.poll();
                    int cy = cur[0], cx = cur[1], dist = cur[2];
                    for (int[] d : deltas) {
                        int ny = cy + d[0], nx = cx + d[1];
                        if (isValid(ny, nx) && grid[ny][nx] != WALL && !visited[ny][nx]) {
                            visited[ny][nx] = true;
                            int nIdx = cellIndex(ny, nx);
                            distMatrix[startIdx][nIdx] = dist + 1;
                            queue.add(new int[] { ny, nx, dist + 1 });
                        }
                    }
                }
            }
        }
    }

    // 승객 선택: 택시 현재 위치에서 가장 가까운 승객을 distMatrix를 이용해 O(1) 조회
    static Passenger findNearestPassenger() {
        int taxiIdx = cellIndex(taxiY, taxiX);
        Passenger candidate = null;
        int minDistance = INF;
        for (int i = 0; i < M; i++) {
            if (isComplete[i])
                continue;
            Passenger p = passengers[i];
            int passengerIdx = cellIndex(p.srcY, p.srcX);
            int d = distMatrix[taxiIdx][passengerIdx];
            if (d >= INF)
                continue; // 도달 불가
            // 최소 거리, 그리고 tie-break: 행 번호, 열 번호 순
            if (d < minDistance) {
                minDistance = d;
                candidate = p;
            } else if (d == minDistance) {
                // tie-break: 행, 그 다음 열
                if (p.srcY < candidate.srcY || (p.srcY == candidate.srcY && p.srcX < candidate.srcX)) {
                    candidate = p;
                }
            }
        }
        // 택시에서 승객까지 갈 수 있고, 연료가 충분해야 함
        if (candidate != null && fuel >= minDistance) {
            return candidate;
        }
        return null;
    }

    // 전체 문제 해결 함수
    static int solve() {
        int completeCount = 0;
        while (completeCount < M) {
            Passenger passenger = findNearestPassenger();
            if (passenger == null)
                return -1;

            int taxiIdx = cellIndex(taxiY, taxiX);
            int passengerIdx = cellIndex(passenger.srcY, passenger.srcX);
            int distanceToPassenger = distMatrix[taxiIdx][passengerIdx];
            if (distanceToPassenger >= INF || fuel < distanceToPassenger)
                return -1;
            fuel -= distanceToPassenger;
            // 택시 위치를 승객 픽업 지점으로 갱신
            taxiY = passenger.srcY;
            taxiX = passenger.srcX;

            // 승객의 출발지에서 목적지까지 거리 조회
            int srcIdx = cellIndex(passenger.srcY, passenger.srcX);
            int dstIdx = cellIndex(passenger.dstY, passenger.dstX);
            int distanceToDestination = distMatrix[srcIdx][dstIdx];
            if (distanceToDestination >= INF || fuel < distanceToDestination)
                return -1;
            fuel -= distanceToDestination;
            // 승객 이동 후 연료 보충 (소모한 연료의 두 배)
            fuel += distanceToDestination * 2;
            // 택시 위치를 승객의 목적지로 갱신
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
            // 입력 순서: 출발지 행, 출발지 열, 목적지 행, 목적지 열 (모두 1씩 빼서 0-index로)
            int srcY = Integer.parseInt(st.nextToken()) - 1;
            int srcX = Integer.parseInt(st.nextToken()) - 1;
            int dstY = Integer.parseInt(st.nextToken()) - 1;
            int dstX = Integer.parseInt(st.nextToken()) - 1;
            passengers[i] = new Passenger(i, srcY, srcX, dstY, dstX);
        }

        // 전처리: 각 셀 간의 최단 거리를 계산
        precomputeDistances();

        bw.write(String.valueOf(solve()));
        bw.close();
        br.close();
    }
}
