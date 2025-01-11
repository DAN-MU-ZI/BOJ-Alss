import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    static boolean redVisited[][];
    static boolean blueVisited[][];
    static int n;
    static int m;
    static int redEndRow;
    static int redEndCol;
    static int blueEndRow;
    static int blueEndCol;
    static int redStartRow;
    static int redStartCol;
    static int blueStartRow;
    static int blueStartCol;
    static final int[][] directions = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    static void block(boolean[][] map, int r, int c) { 
        map[r][c] = true;
    }
    
    static void unblock(boolean[][] map, int r, int c) { 
        map[r][c] = false;
    }

    static void initMap(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        
        redVisited = new boolean[n][m];
        blueVisited = new boolean[n][m];
        
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                switch(maze[r][c]) {
                    case 1: {
                        block(redVisited, r, c);
                        redStartRow = r;
                        redStartCol = c;
                        break;
                    }
                    case 2: {
                        block(blueVisited, r, c);
                        blueStartRow = r;
                        blueStartCol = c;
                        break;
                    }
                    case 3: {
                        redEndRow = r;
                        redEndCol = c;
                        break;
                    }
                    case 4: {
                        blueEndRow = r;
                        blueEndCol = c;
                        break;
                    }
                    case 5: {
                        block(redVisited, r, c);
                        block(blueVisited, r, c);
                        break;
                    }
                }
            }
        }
    }
    
    public int solution(int[][] maze) {
        initMap(maze);
        searchMap(maze, redStartRow, redStartCol, blueStartRow, blueStartCol, 0);
        
        return this.answer == Integer.MAX_VALUE ? 0 : this.answer;
    }
    
    static boolean isValidRange(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }
    
    static boolean isValidRange(int rr, int rc, int br, int bc) {
        return isValidRange(rr, rc) && isValidRange(br, bc);
    }
    
    static boolean isReach(int rr, int rc, int br, int bc) {
        return redEndRow == rr && redEndCol == rc && blueEndRow == br && blueEndCol == bc;
    }
    
    static void searchMap(int[][]maze, int rr, int rc, int br, int bc, int phase) {
        // System.out.println(String.format("%d %d %d %d %d", rr, rc, br, bc, phase));
        // for (int r = 0; r < n; r++) {
        //     System.out.print(Arrays.toString(redVisited[r]) + Arrays.toString(blueVisited[r]));
        //     System.out.println();
        // }
        // System.out.println();
        
        
        if (isReach(rr, rc, br, bc)) {
            answer = Math.min(answer, phase);
            return;
        }
        
        for (int[] redDirection: directions) {            
            int nrr = rr + redDirection[0];
            int nrc = rc + redDirection[1];
            
            
            for (int[] blueDirection: directions) {
                int nbr = br + blueDirection[0];
                int nbc = bc + blueDirection[1];
                
                if (rr == redEndRow && rc == redEndCol) {
                    if (!isValidRange(nbr, nbc)) continue;
                    if (blueVisited[nbr][nbc]) continue;
                    if (rr == nbr && rc == nbc) continue;
                    
                    block(blueVisited, nbr, nbc);

                    searchMap(maze, rr, rc, nbr, nbc, phase + 1);

                    unblock(blueVisited, nbr, nbc);
                } else if (br == blueEndRow && bc == blueEndCol) {
                    if (!isValidRange(nrr, nrc)) continue;
                    if (redVisited[nrr][nrc]) continue;
                    if (nrr == br && nrc == bc) continue;

                    block(redVisited, nrr, nrc);

                    searchMap(maze, nrr, nrc, br, bc, phase + 1);

                    unblock(redVisited, nrr, nrc);
                } else {
                    if (!isValidRange(nrr, nrc, nbr, nbc)) continue;
                    
                    if (nrr == br && nrc == bc && nbr == rr && nbc == rc) continue;
                    if (nrr == nbr && nrc == nbc) continue;
                    
                    if (blueVisited[nbr][nbc]) continue;
                    if (redVisited[nrr][nrc]) continue;
                    
                    block(redVisited, nrr, nrc);
                    block(blueVisited, nbr, nbc);

                    searchMap(maze, nrr, nrc, nbr, nbc, phase + 1);

                    unblock(redVisited, nrr, nrc);
                    unblock(blueVisited, nbr, nbc);
                }
            }
        }
    }
}