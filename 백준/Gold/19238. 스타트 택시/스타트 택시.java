import java.io.*;
import java.util.*;

public class Main {
    static final int WALL = 1;
    static final int INF = Integer.MAX_VALUE;
    static int N, M, fuel;
    static int[][] grid;
    
    // 택시의 현재 위치 (행, 열)
    static int taxiY, taxiX;
    
    static Passenger[] passengers;
    static boolean[] isComplete;
    
    // 전체 셀 개수
    static int totalCells;
    // lazy cache: lazyCache[startIdx]가 null이면 아직 계산되지 않은 상태,
    // 계산되면 startIdx에서 각 셀까지의 최단 거리가 저장된 배열을 갖습니다.
    static Integer[][] lazyCache;
    
    // 상, 좌, 하, 우 이동 (행, 열 순서)
    static int[][] deltas = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} };
    
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
    
    // 격자 내 좌표 유효성 검사
    static boolean isValid(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
    
    // (y, x)를 1차원 인덱스로 변환 (0-indexed)
    static int cellIndex(int y, int x) {
        return y * N + x;
    }
    
    // lazy 캐시 초기화: 각 행은 아직 null로 남아 계산되지 않은 상태입니다.
    static void initLazyCache() {
        totalCells = N * N;
        lazyCache = new Integer[totalCells][];
    }
    
    // startIdx에서 endIdx까지의 최단 거리를 lazy 캐시에서 반환합니다.
    // 만약 startIdx에 대해 아직 BFS 계산이 되지 않았다면, 계산한 후 캐시에 저장합니다.
    static int getLazyDistance(int startIdx, int endIdx) {
        if (lazyCache[startIdx] == null) {
            lazyCache[startIdx] = new Integer[totalCells];
            Arrays.fill(lazyCache[startIdx], INF);
            int startY = startIdx / N;
            int startX = startIdx % N;
            boolean[][] visited = new boolean[N][N];
            Queue<int[]> queue = new ArrayDeque<>();
            visited[startY][startX] = true;
            lazyCache[startIdx][startIdx] = 0;
            queue.add(new int[] { startY, startX, 0 });
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int y = cur[0], x = cur[1], dist = cur[2];
                int curIdx = cellIndex(y, x);
                lazyCache[startIdx][curIdx] = dist;
                for (int[] d : deltas) {
                    int ny = y + d[0], nx = x + d[1];
                    if (isValid(ny, nx) && grid[ny][nx] != WALL && !visited[ny][nx]) {
                        visited[ny][nx] = true;
                        queue.add(new int[] { ny, nx, dist + 1 });
                    }
                }
            }
        }
        return lazyCache[startIdx][endIdx];
    }
    
    // 택시 현재 위치에서 가장 가까운 승객을 찾습니다.
    // tie-break 기준: 최소 거리, 그리고 행 번호, 열 번호 순
    static Passenger findNearestPassenger() {
        int taxiIdx = cellIndex(taxiY, taxiX);
        Passenger candidate = null;
        int minDistance = INF;
        for (int i = 0; i < M; i++) {
            if (isComplete[i]) continue;
            Passenger p = passengers[i];
            int passengerIdx = cellIndex(p.srcY, p.srcX);
            int d = getLazyDistance(taxiIdx, passengerIdx);
            if (d >= INF) continue; // 도달 불가능
            if (d < minDistance) {
                minDistance = d;
                candidate = p;
            } else if (d == minDistance) {
                // tie-break: 행 번호, 그 다음 열 번호
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
    
    // 전체 문제 해결: 각 단계마다 lazy 캐시를 활용해 필요한 거리를 계산합니다.
    static int solve() {
        int completeCount = 0;
        while (completeCount < M) {
            Passenger p = findNearestPassenger();
            if (p == null) return -1;
            int taxiIdx = cellIndex(taxiY, taxiX);
            int passengerIdx = cellIndex(p.srcY, p.srcX);
            int distToPassenger = getLazyDistance(taxiIdx, passengerIdx);
            if (distToPassenger >= INF || fuel < distToPassenger) return -1;
            fuel -= distToPassenger;
            // 택시 위치를 승객 픽업 지점으로 갱신
            taxiY = p.srcY;
            taxiX = p.srcX;
            
            int srcIdx = cellIndex(p.srcY, p.srcX);
            int dstIdx = cellIndex(p.dstY, p.dstX);
            int distToDestination = getLazyDistance(srcIdx, dstIdx);
            if (distToDestination >= INF || fuel < distToDestination) return -1;
            fuel -= distToDestination;
            // 승객 이동 후 연료 보충 (소모한 연료의 두 배)
            fuel += distToDestination * 2;
            // 택시 위치를 승객 목적지로 갱신
            taxiY = p.dstY;
            taxiX = p.dstX;
            
            isComplete[p.id] = true;
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
        
        // lazy 캐시 초기화
        initLazyCache();
        
        bw.write(String.valueOf(solve()));
        bw.close();
        br.close();
    }
}
