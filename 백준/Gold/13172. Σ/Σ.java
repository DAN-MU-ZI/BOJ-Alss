import java.io.*;
import java.util.*;

public class Main {
    private static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int M = Integer.parseInt(br.readLine());
        long totalExpectation = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            long currentExpectation = (S * modPow(N, MOD - 2)) % MOD;

            totalExpectation = (totalExpectation + currentExpectation) % MOD;
        }
        System.out.println(totalExpectation);
    }

    public static long modPow(long base, long exp) {
        long result = 1;
        long n = base % MOD;

        while (exp > 0) {
            if ((exp % 2) == 1) {
                result = (result * n) % MOD;
            }
            n = (n * n) % MOD;
            exp /= 2;
        }
        return result;
    }
}