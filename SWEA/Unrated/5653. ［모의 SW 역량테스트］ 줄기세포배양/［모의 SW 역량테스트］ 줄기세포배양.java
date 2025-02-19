import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, K;
    static int[][] grid;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {            
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            grid = new int [N][M];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    grid[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            int answer = solve();
            sb.append(String.format("#%d %s\n", t, answer));
        }
        
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static Queue<Cell> actQ, actTmpQ, deactQ, deactTmpQ;
    static Map<String, Cell> map, aliveMap;
    static int solve() {
        init();
        
        while (K-- > 0) {
            actTmpQ = new ArrayDeque<>();
            deactTmpQ = new ArrayDeque<>();

            // 활성화 처리
            processActivatedCell();
            
            // 비활성화 처리
            processDeactivatedCell();
            
            actQ = actTmpQ;
            deactQ = deactTmpQ;
        }

        return countAliveCell();
    }

    static final int DEACT = 0, ACT = 1, DEAD = 2;
    static public class Cell {
        public int r, c, x, a, da, state;
        
        public Cell(int r, int c, int x) {
            this.r = r;
            this.c = c;
            this.x = x;
            this.a = 0;
            this.da = 0;
            this.state = DEACT;
        }
    }

    static void init() {
        actQ = new ArrayDeque<>();
        
        deactQ = new ArrayDeque<>();

        map = new HashMap<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (grid[r][c] > 0) {
                    Cell cell = new Cell(r, c, grid[r][c]);
                    deactQ.add(cell);
                    String hash = getHash(r, c);
                    map.put(hash, cell);
                }
            }
        }
    }

    static void processActivatedCell() {
        aliveMap = new HashMap<>();

        while (!actQ.isEmpty()) {
            Cell cell = actQ.poll();
            cell.a += 1;

            if (cell.a == 1) {
                placeCell(cell);
            }

            if (cell.a == cell.x) {
                cell.state = DEAD;
            } else {
                actTmpQ.add(cell);
            }
        }

        for (String key: aliveMap.keySet()) {
            if (map.getOrDefault(key, null)==null) {
                Cell newCell = aliveMap.get(key);
                map.put(key, newCell);
                deactTmpQ.add(newCell);
            }
        }
    }

    static String getHash(int r, int c) {
        return Arrays.toString(new int[] {r, c});
    }

    static void placeCell(Cell cell) { 
        int[][] deltas = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
        for (int[] delta: deltas) {
            int r = cell.r + delta[0];
            int c = cell.c + delta[1];
            String hash = getHash(r, c);

            Cell targetCell = aliveMap.getOrDefault(hash, null);
            Cell newCell = new Cell(r, c, cell.x);

            if (targetCell == null) {
                aliveMap.put(hash, newCell);
            } else if (!map.containsKey(hash) && cell.x > targetCell.x) {
                aliveMap.put(hash, newCell);
            }
        }
    }
    
    static void processDeactivatedCell() {
        while (!deactQ.isEmpty()) {
            Cell cell = deactQ.poll();
            cell.da += 1;

            if (cell.da == cell.x) {
                cell.state = ACT;
                actTmpQ.add(cell);
            } else {
                deactTmpQ.add(cell);
            }
        }
    }

    static int countAliveCell() { 
        return actQ.size() + deactQ.size();
    }
}