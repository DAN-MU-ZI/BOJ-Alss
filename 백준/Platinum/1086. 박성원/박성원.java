import java.util.Scanner;

public class Main {
    static long[] dp;        // 1D 배열: dp[mask*K + r]
    static long[] factorial; // 팩토리얼 (N!까지)
    static long[] mods;      // 각 숫자를 K로 나눈 나머지
    static long[] lenPow;    // 각 숫자의 길이에 대응하는 (10^길이) % K
    static int[] pow10;      // 10^i % K, i=0..50
    
    // 최대공약수
    static long gcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String[] nums = new String[N];
        for(int i = 0; i < N; i++){
            nums[i] = sc.next();
        }
        int K = sc.nextInt();
        
        // 1. factorial (N!까지) 미리 계산
        factorial = new long[N + 1];
        factorial[0] = 1;
        for(int i = 1; i <= N; i++) {
            factorial[i] = factorial[i - 1] * i; 
        }
        
        // 2. pow10[i] = (10^i) % K (i=0..50)
        pow10 = new int[51];
        pow10[0] = 1 % K;
        for(int i = 1; i <= 50; i++){
            pow10[i] = (pow10[i - 1] * 10) % K;
        }
        
        // 3. 각 숫자에 대해 mods, lenPow 계산
        mods = new long[N];
        lenPow = new long[N];
        for(int i = 0; i < N; i++){
            // mods[i] = nums[i] % K
            long m = 0;
            for(char c : nums[i].toCharArray()){
                m = (m * 10 + (c - '0')) % K;
            }
            mods[i] = m;
            
            // lenPow[i] = 10^(길이(nums[i])) % K
            int lengthOfNum = nums[i].length();
            lenPow[i] = pow10[lengthOfNum];
        }
        
        // 4. DP 배열 준비: 크기 = (1<<N) * K
        //   dp[mask*K + r] = dp[mask][r]
        //   메모리는 대략 (2^15 * 100) * 8 bytes ~= 25.6MB (raw) + 오버헤드
        //   2차원 배열보다 오버헤드가 줄어듦
        dp = new long[(1 << N) * K];
        
        // 초기값: dp[0][0] = 1
        dp[0] = 1;  // (mask=0, r=0)
        
        // 5. 점화
        for(int mask = 0; mask < (1 << N); mask++){
            for(int r = 0; r < K; r++){
                long currentWays = dp[mask * K + r];
                if(currentWays == 0) continue; // 스킵
                
                // 아직 사용하지 않은 숫자 i를 붙인다
                for(int i = 0; i < N; i++){
                    if((mask & (1 << i)) != 0) {
                        // 이미 사용 중인 숫자
                        continue;
                    }
                    int newMask = mask | (1 << i);
                    
                    // 새 나머지 계산
                    // newR = (r * lenPow[i] + mods[i]) % K
                    long newR = ( (r * lenPow[i]) % K + mods[i] ) % K;
                    
                    dp[newMask * K + (int)newR] += currentWays;
                }
            }
        }
        
        // 6. 결과: dp[((1<<N) - 1) * K + 0] => 모든 숫자 사용 & 나머지 0
        long p = dp[((1 << N) - 1) * K];
        long q = factorial[N]; // 전체 순열 개수
        
        // 7. 기약분수 출력
        if(p == 0) {
            System.out.println("0/1");
            return;
        }
        if(p == q) {
            System.out.println("1/1");
            return;
        }
        
        long g = gcd(p, q);
        p /= g;
        q /= g;
        
        System.out.println(p + "/" + q);
    }
}
