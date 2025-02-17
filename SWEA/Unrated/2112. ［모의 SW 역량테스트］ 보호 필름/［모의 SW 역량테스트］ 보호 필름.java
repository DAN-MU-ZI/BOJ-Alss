import java.io.*;
import java.util.*;

public class Solution {
    static int D, W, K;
    static int[] cells;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            
            // cells[j]는 j번째 열의 상태를 D비트 정수로 저장 (상단행이 최상위 비트)
            cells = new int[W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    cells[j] = (cells[j] << 1) | Integer.parseInt(st.nextToken());
                }
            }
            
            int answer = solve();
            sb.append(String.format("#%d %d\n", t, answer));
        }
        
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
    
    // film 전체가 조건(각 열에 K연속 동일 셀 존재)을 만족하는지 검사
    static boolean testFilm(int[] film) {
        for (int j = 0; j < W; j++) {
            if (!checkColumn(film[j])) return false;
        }
        return true;
    }
    
    // 주입 정보를 적용한 film이 조건을 만족하는지 검사 (injMask와 injVal는 film의 좌표에 맞게 "역순" 변환되어 있음)
    static boolean testFilmWithInjection(int injMask, int injVal) {
        for (int j = 0; j < W; j++) {
            // 해당 열에서 주입한 행은 injMask에 해당하는 비트를 injVal로 강제 대체
            int newCol = (cells[j] & ~injMask) | injVal;
            if (!checkColumn(newCol)) return false;
        }
        return true;
    }
    
    // 각 열(정수로 표현된 D비트)에서 연속된 K개의 셀이 모두 0 또는 모두 1인지 검사
    static boolean checkColumn(int col) {
        int pattern = (1 << K) - 1; // K개의 1
        // film의 행은 상단행부터 연속되어 있어, newCol의 비트는 [bit(D-1), ..., bit0]에 저장됨.
        // 예를 들어, 행 0~K-1(상단 K행)은 newCol >> (D-K) 의 하위 K비트에 해당함.
        for (int start = 0; start <= D - K; start++) {
            int seg = (col >> (D - K - start)) & pattern;
            if (seg == 0 || seg == pattern) return true;
        }
        return false;
    }
    
    // bitmask에 저장된 행 번호(0~D-1; 0은 최상단)가 film 좌표(최상위 비트가 행0)가 되도록 변환
    static int transform(int mask) {
        int res = 0;
        for (int i = 0; i < D; i++) {
            if ((mask & (1 << i)) != 0) {
                res |= (1 << (D - 1 - i));
            }
        }
        return res;
    }
    
    // 비트마스크를 이용하여 주입 횟수를 최소로 하는 경우를 찾음.
    static int solve() {
        // 먼저 주입 없이도 조건 통과하면 바로 0 반환
        if (testFilm(cells)) return 0;
        
        // 주입 횟수를 1부터 D까지 늘려가며 시도
        for (int cnt = 1; cnt <= D; cnt++) {
            // D개의 행 중 cnt개를 선택하는 모든 subset (각 subset은 0~(1<<D)-1 중 bitCount == cnt)
            for (int sub = 0; sub < (1 << D); sub++) {
                if (Integer.bitCount(sub) != cnt) continue;
                // sub: 주입할 행을 결정 (비트 i가 1이면 row i에 약품 주입)
                // 각 주입 행마다 A와 B 중 어느 약품을 사용할지 결정 (A는 0, B는 1)
                // drug: 주입할 행에 대해, bit i가 1이면 B, 0이면 A. 단, sub에 포함되지 않은 행은 무시해야 하므로 drug는 sub의 부분집합여야 함.
                for (int drug = 0; drug < (1 << D); drug++) {
                    if ((drug & ~sub) != 0) continue; // 주입하지 않는 행에 대해 값이 있으면 skip
                    int injMask = transform(sub);
                    int injVal = transform(drug);
                    if (testFilmWithInjection(injMask, injVal)) return cnt;
                }
            }
        }
        return D;
    }
}
