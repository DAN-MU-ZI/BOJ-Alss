import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] m, c;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        m = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
        }

        c = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(solve()));
        
        bw.close();
        br.close();
    }

    static int solve() {
        int[] dp = new int[10001];

        for (int i = 0; i < N; i++) {
            int memory = m[i];
            int cost = c[i];
            for (int v = 10000; v >= cost; v--) {
                dp[v] = Math.max(dp[v], dp[v - cost] + memory);
            }
        }

        for (int i = 0; i <= 10000; i++) {
            if (dp[i] >= M) {
                return i;
            }
        }
        return -1;
    }
}