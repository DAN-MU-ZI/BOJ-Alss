import java.io.*;
import java.util.*;

public class Solution {
    static int[] cards, rev;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {            
            st = new StringTokenizer(br.readLine());
            cards = new int[10];
            for (int i = 1 ; i <= 9; i++) {
                cards[i] = Integer.parseInt(st.nextToken());
            }

            String answer = solve();
            sb.append(String.format("#%d %s\n", t, answer));
        }
        
        bw.write(sb.toString());

        bw.close();
        br.close();
    }

    static String solve() {
        boolean[] deck = new boolean[19];
        rev = new int[10];
        young = new int[10];
        visited = new boolean[10];
        cnt = 0;
        for (int i = 1; i <= 9; i++) {
            deck[cards[i]] = true;
        }

        int idx = 1;
        for (int i = 1; i <= 18; i++) {
            if (!deck[i]) {
                rev[idx] = i;
                idx++;
            }
        }

        dfs(0);
        
        return String.format("%d %d", cnt, (362880 - cnt));
    }

    static int cnt;
    static boolean[] visited;
    static int[] young;
    static void dfs(int idx) {
        if (idx == 9) {
            int k = 0, y = 0;
            for (int i = 1; i <= 9; i++) {
                if (cards[i] > young[i]) {
                    k += cards[i] + young[i];
                } else {
                    y += cards[i] + young[i];
                }
            }

            if (k > y) {
                cnt++;
            }
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            young[idx + 1] = rev[i];
            dfs(idx+1);
            visited[i] = false;
        }
    }
}