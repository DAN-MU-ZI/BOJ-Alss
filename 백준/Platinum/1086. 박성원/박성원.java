import java.io.*;
import java.util.*;

public class Main {
    static long[] dp;            // 1차원 DP 배열: dp[mask * K + r]
    static long[] factorial;     // 팩토리얼 (N!까지)
    static int[] mods;           // 각 숫자를 K로 나눈 나머지
    static int[][] nextR;        // nextR[i][r]: i번 숫자를 추가했을 때 새 나머지
    static int[] lenPow;         // 각 숫자의 길이에 해당하는 (10^길이) % K
    static int[] pow10;          // 10^i % K, i = 0..50

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        String[] nums = new String[N];
        for (int i = 0; i < N; i++) {
            nums[i] = br.readLine();
        }
        int K = Integer.parseInt(br.readLine());

        // 1. 팩토리얼 계산 (long 타입)
        factorial = new long[N + 1];
        factorial[0] = 1;
        for (int i = 1; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        // 2. pow10[i] = (10^i) % K
        pow10 = new int[51];
        pow10[0] = 1 % K;
        for (int i = 1; i <= 50; i++) {
            pow10[i] = (pow10[i - 1] * 10) % K;
        }

        // 3. mods[i]와 lenPow[i] 계산
        mods = new int[N];
        lenPow = new int[N];
        for (int i = 0; i < N; i++) {
            long m = 0;
            for (char c : nums[i].toCharArray()) {
                m = (m * 10 + (c - '0')) % K;
            }
            mods[i] = (int) m;
            lenPow[i] = pow10[nums[i].length()];
        }

        // 4. nextR[i][r] 계산
        nextR = new int[N][K];
        for (int i = 0; i < N; i++) {
            for (int r = 0; r < K; r++) {
                nextR[i][r] = (int) (((r * (long) lenPow[i]) % K + mods[i]) % K);
            }
        }

        // 5. DP 배열 초기화
        dp = new long[(1 << N) * K];
        dp[0] = 1;  // mask = 0, r = 0

        // 6. DP 점화
        for (int mask = 0; mask < (1 << N); mask++) {
            for (int r = 0; r < K; r++) {
                long ways = dp[mask * K + r];
                if (ways == 0) continue;
                for (int i = 0; i < N; i++) {
                    if ((mask & (1 << i)) != 0) continue;
                    int newMask = mask | (1 << i);
                    int newR = nextR[i][r];
                    dp[newMask * K + newR] += ways;
                }
            }
        }

        // 7. 결과: dp[((1 << N) - 1) * K + 0]
        long p = dp[((1 << N) - 1) * K];
        long q = factorial[N];

        // 8. 기약분수로 출력
        if (p == 0) {
            bw.write("0/1\n");
        } else if (p == q) {
            bw.write("1/1\n");
        } else {
            long g = gcd(p, q);
            bw.write((p / g) + "/" + (q / g) + "\n");
        }

        bw.flush();
    }

    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
