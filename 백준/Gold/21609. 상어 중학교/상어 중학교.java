import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] grid;
    static boolean[][] isBlank; // 제거된 칸 표시(-2)

    // tie-break 비교에 필요한 전역 변수들
    // (현재까지 발견한 '최적 그룹'에 대한 정보)
    static int bestSize, bestRainbow, bestStdRow, bestStdCol;
    static boolean[][] bestVisited; // 그 최적 그룹의 방문 배열(= toRemove)

    // BFS 시 사용하는 방향
    static final int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
    // 상수 정의
    static final int BLACK = -1;
    static final int RAINBOW = 0;
    static final int EMPTY = -2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        grid = new int[N][N];
        for(int r = 0; r < N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c < N; c++){
                grid[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        isBlank = new boolean[N][N]; // 처음엔 아무 칸도 제거되지 않은 상태

        int answer = 0;
        while(true) {
            // 1) findGroup() → 전역변수(bestSize 등) 갱신
            if(!findGroup()) break; // 그룹이 없다면 종료

            // 2) bestVisited 제거 → 점수 계산
            answer += remove();  // (size * size) 점수 획득

            // 3) 중력
            gravity();
            // 4) 반시계 90도 회전
            rotate();
            // 5) 다시 중력
            gravity();
        }
        System.out.println(answer);
    }

    // (A) 모든 칸을 돌며 '최적의 그룹'을 찾는다.
    //    찾으면 true, 못 찾으면 false
    static boolean findGroup(){
        // 우선 '최적 그룹' 정보를 초기화
        bestSize = 0;
        bestRainbow = 0;
        bestStdRow = -1; 
        bestStdCol = -1;
        bestVisited = null;

        // 각 색상 블록 방문을 막기 위한 배열
        boolean[][] map = new boolean[N][N];

        // 모든 칸을 돌면서 일반 블록(1~M)이고 아직 제거되지 않았으며, 방문 안 한 곳이면 BFS 시도
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                int color = grid[r][c];
                if(color > 0 && color <= M && !isBlank[r][c] && !map[r][c]){
                    // BFS
                    BfsResult res = bfs(r, c, color, map);
                    if(res == null) continue; // 그룹 크기 < 2

                    // tie-break 비교
                    // (크기가 더 크면 갱신)
                    if(res.size > bestSize) {
                        updateBestGroup(res);
                    }
                    // (크기가 같으면 무지개 수 비교)
                    else if(res.size == bestSize && res.rainbow > bestRainbow) {
                        updateBestGroup(res);
                    }
                    // (둘 다 같으면 '기준 블록 행' 비교 - 내림차순)
                    else if(res.size == bestSize && res.rainbow == bestRainbow) {
                        if(res.stdRow > bestStdRow) {
                            updateBestGroup(res);
                        }
                        // (행까지 같으면 '기준 블록 열' 비교 - 내림차순)
                        else if(res.stdRow == bestStdRow && res.stdCol > bestStdCol){
                            updateBestGroup(res);
                        }
                    }
                }
            }
        }

        // 최종적으로 bestSize < 2 이면 그룹이 없는 것
        return (bestSize >= 2);
    }

    // (B) BFS 결과를 저장할 구조체(대신 간단한 클래스, 또는 배열) 대신 이렇게 담아둠
    static class BfsResult {
        int size;       // 그룹 전체 크기
        int rainbow;    // 무지개 블록 개수
        int stdRow;     // 기준 블록 행 (가장 위, 가장 왼쪽)
        int stdCol;     // 기준 블록 열
        boolean[][] visited; // 이 그룹에 속한 위치 표시 (제거할 때 사용)
        public BfsResult(int size, int rainbow, int stdR, int stdC, boolean[][] v){
            this.size = size;
            this.rainbow = rainbow;
            this.stdRow = stdR;
            this.stdCol = stdC;
            this.visited = v;
        }
    }

    // (B) BFS: (sr, sc)에서 시작하는 color그룹 찾기
    static BfsResult bfs(int sr, int sc, int color, boolean[][] map){
        // visited: 이번 BFS에서 임시 사용
        // map: 색상 블록(일반 블록)은 전역적으로 중복 탐색 안 하도록
        boolean[][] visited = new boolean[N][N];
        visited[sr][sc] = true;
        map[sr][sc] = true; // 일반 블록이면 이후에 다시 BFS 안 함

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});

        // 그룹에 속한 칸 정보
        ArrayList<int[]> groupBlocks = new ArrayList<>();
        groupBlocks.add(new int[]{sr, sc});

        // 무지개 블록 리스트 (BFS 종료 후 visited 해제용)
        ArrayList<int[]> rainbowList = new ArrayList<>();

        // 기준 블록(가장 위, 왼쪽 - 무지개 블록 제외)
        int stdRow = sr, stdCol = sc;

        int size = 1;
        int rainbowCount = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            for(int[] d : deltas){
                int nr = r + d[0];
                int nc = c + d[1];
                if(!inRange(nr, nc)) continue;
                if(isBlank[nr][nc]) continue; // 제거된 칸(-2)
                if(grid[nr][nc] == BLACK) continue; // 검은색(-1)은 X
                if(visited[nr][nc]) continue;

                // 일반 블록(= color) or 무지개 블록(=0)이면 그룹에 포함
                if(grid[nr][nc] == color || grid[nr][nc] == RAINBOW){
                    visited[nr][nc] = true;
                    q.add(new int[]{nr, nc});
                    groupBlocks.add(new int[]{nr, nc});
                    size++;

                    if(grid[nr][nc] == RAINBOW){
                        rainbowCount++;
                        rainbowList.add(new int[]{nr, nc});
                    } else {
                        // 일반 블록이면, map에도 표시해서 중복 BFS 안 함
                        map[nr][nc] = true;
                        // 기준 블록 업데이트 (더 위, 더 왼쪽)
                        if(nr < stdRow || (nr == stdRow && nc < stdCol)){
                            stdRow = nr;
                            stdCol = nc;
                        }
                    }
                }
            }
        }

        // 무지개 블록은 다른 색상에서 다시 쓰일 수 있으므로 방문 해제
        for(int[] rb : rainbowList){
            visited[rb[0]][rb[1]] = false;
        }

        // 그룹 크기 < 2 면 null
        if(size < 2) return null;

        // 방문 배열을 복사해서 (추가로) 저장
        boolean[][] mask = new boolean[N][N];
        for(int[] pos : groupBlocks){
            mask[pos[0]][pos[1]] = true;
        }

        return new BfsResult(size, rainbowCount, stdRow, stdCol, mask);
    }

    // (C) tie-break에서 더 나은 그룹을 찾으면 전역변수 갱신
    static void updateBestGroup(BfsResult res){
        bestSize = res.size;
        bestRainbow = res.rainbow;
        bestStdRow = res.stdRow;
        bestStdCol = res.stdCol;
        bestVisited = res.visited;
    }

    // (D) bestVisited를 -2(EMPTY)로 지우고, score = size^2
    static int remove(){
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                if(bestVisited != null && bestVisited[r][c]){
                    grid[r][c] = EMPTY;
                    isBlank[r][c] = true;
                }
            }
        }
        return bestSize*bestSize; 
    }

    // (E) 중력
    static void gravity(){
        for(int c=0; c<N; c++){
            for(int r=N-1; r>=0; r--){
                // 검은색(-1)은 이동 안 함
                if(grid[r][c] == BLACK) continue;
                if(grid[r][c] == EMPTY) continue;

                int nr = r;
                while(true){
                    int down = nr+1;
                    if(!inRange(down, c)) break;
                    if(grid[down][c] != EMPTY) break; // 아래가 빈칸이 아니면 멈춤
                    nr++;
                }
                if(nr != r){
                    // 내려갈 위치에 블록 복사
                    grid[nr][c] = grid[r][c];
                    // 원 위치는 빈 칸
                    grid[r][c] = EMPTY;
                    // isBlank도 같이 이동
                    isBlank[nr][c] = false;
                    isBlank[r][c] = true;
                }
            }
        }
    }

    // (F) 90도 반시계 회전
    static void rotate(){
        int[][] newGrid = new int[N][N];
        boolean[][] newBlank = new boolean[N][N];

        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                newGrid[N-1-c][r] = grid[r][c];
                newBlank[N-1-c][r] = isBlank[r][c];
            }
        }
        grid = newGrid;
        isBlank = newBlank;
    }

    static boolean inRange(int r, int c){
        return (r>=0 && r<N && c>=0 && c<N);
    }
}
