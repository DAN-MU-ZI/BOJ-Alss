import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 입출력 기본 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        
        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int answer = Solution.solve(N, M, D, grid);
        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
    
    static class Solution {
        static int N, M, D, answer;
        static int[][] grid;
        
        public static int solve(int N, int M, int D, int[][] grid) {
            init(N, M, D, grid);
            
            // 궁수 3명의 배치 조합 생성
            List<int[]> archerCombinations = new ArrayList<>();
            generateCombination(new int[3], 0, 0, M, archerCombinations);
            
            for (int[] positions : archerCombinations) {
                simulate(positions);
            }
            
            return answer;
        }
        
        // 재귀로 3개 위치 조합 생성 (예: [0, 2, 4] 등)
        static void generateCombination(int[] comb, int start, int depth, int M, List<int[]> list) {
            if (depth == 3) {
                list.add(comb.clone());
                return;
            }
            for (int i = start; i < M; i++) {
                comb[depth] = i;
                generateCombination(comb, i + 1, depth + 1, M, list);
            }
        }
        
        // 각 배치에 대해 게임 시뮬레이션 진행
        static void simulate(int[] positions) {
            // 원본 grid 복사 (시뮬레이션 격자)
            int[][] simGrid = new int[N][M];
            for (int i = 0; i < N; i++) {
                System.arraycopy(grid[i], 0, simGrid[i], 0, M);
            }
            
            int kills = 0;
            int remaining = countEnemies(simGrid);
            
            // 적이 남아있는 동안 턴 진행
            while (remaining > 0) {
                // 각 궁수마다 공격할 타깃을 찾는다.
                // 중복 공격이 일어나더라도 한 번에 처리하기 위해 좌표를 인코딩해서 Set에 저장한다.
                Set<Integer> targets = new HashSet<>();
                for (int archer : positions) {
                    int[] target = findTarget(archer, simGrid);
                    if (target != null) {
                        int code = target[0] * M + target[1];
                        targets.add(code);
                    }
                }
                
                // 중복 없이 타깃을 제거
                for (int code : targets) {
                    int r = code / M;
                    int c = code % M;
                    if (simGrid[r][c] == 1) {
                        simGrid[r][c] = 0;
                        kills++;
                        remaining--;
                    }
                }
                
                // 적 이동: 아래로 한 칸 이동 (궁수(아래쪽) 쪽에 도달하면 격자에서 제거)
                remaining = moveDown(simGrid, remaining);
            }
            
            answer = Math.max(answer, kills);
        }
        
        // 현재 격자에서 살아있는 적의 수 세기
        static int countEnemies(int[][] simGrid) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (simGrid[i][j] == 1) count++;
                }
            }
            return count;
        }
        
        // 격자 아래로 이동 (마지막 행의 적은 제거)
        static int moveDown(int[][] simGrid, int remaining) {
            // 마지막 행에 있는 적 제거
            for (int j = 0; j < M; j++) {
                if (simGrid[N - 1][j] == 1) {
                    simGrid[N - 1][j] = 0;
                    remaining--;
                }
            }
            // 행 복사: 바로 위 행을 아래 행에 복사 (System.arraycopy 사용)
            for (int i = N - 1; i > 0; i--) {
                System.arraycopy(simGrid[i - 1], 0, simGrid[i], 0, M);
            }
            // 첫 행은 0으로 채움
            Arrays.fill(simGrid[0], 0);
            return remaining;
        }
        
        // 각 궁수에 대해 사정거리 내의 적 중 [가장 가까운, 같다면 가장 왼쪽] 적 선택
        // 궁수는 고정된 위치 (N, archerCol)에 있으므로, 맨해튼 거리는 (N - r) + |c - archerCol|
        static int[] findTarget(int archerCol, int[][] simGrid) {
            int targetR = -1, targetC = -1;
            int minDist = D + 1; // D보다 큰 값으로 초기화
            
            // 모든 격자 칸 순회 (격자의 하단부터 탐색해도 되지만, 명시적으로 거리를 계산)
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (simGrid[r][c] == 1) {
                        int dist = (N - r) + Math.abs(c - archerCol);
                        if (dist <= D) {
                            if (dist < minDist) {
                                minDist = dist;
                                targetR = r;
                                targetC = c;
                            } else if (dist == minDist && c < targetC) {
                                targetR = r;
                                targetC = c;
                            }
                        }
                    }
                }
            }
            
            if (targetR == -1) return null;
            return new int[]{targetR, targetC};
        }
        
        static void init(int N, int M, int D, int[][] grid) {
            Solution.N = N;
            Solution.M = M;
            Solution.D = D;
            Solution.grid = grid;
            answer = 0;
        }
    }
}
