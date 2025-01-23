import java.io.*;
import java.util.*;

class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
        int M = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] cards = new int[K];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int card = Integer.parseInt(st.nextToken());
            cards[i] = card;
        }

        Solution solution = new Solution(M, K, cards);
        long answer = solution.solve();
        sb.append(answer);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

    private static class Solution {
        private final int M;
        private final int K;
        private int[] cards;

        public Solution(int M, int K, int[] cards) {
            this.M = M;
            this.K = K;
            this.cards = cards;
        }

        public long solve() {
            int bufferSize = 3000;
            boolean[] dp = new boolean[bufferSize + 1];
            for (int i = 1; i <= bufferSize; i++) {
                for (int j = 0; j < K && i - cards[j] >= 0; j++) {
                    if (dp[i - cards[j]] == false) {
                        dp[i] = true;
                        break;
                    }
                }
            }

            int startPoint = -1;
            int period = -1;
            outer:
            for (int start = 1; start <= bufferSize; start++) {
				for (int term = 1; start + 2 * term <= bufferSize; term++) {
					if (checkPeriod(dp, start, term)) {
						startPoint = start;
						period = term;
						break outer;
					}
				}
			}


            if (startPoint == -1 || period == -1) {
                if (M <= bufferSize) {
                    long loseCount = 0;
                    for (int i = 1; i <= M; i++) {
                        if (!dp[i]) {
                            loseCount++;
                        }
                    }
                    return loseCount;
                } else {
                    return -1;
                }
            }

            long startCnt = 0;
            for (int i = 1; i < startPoint; i++) {
                if (dp[i] == false) startCnt++;
            }
            long termCnt = 0;
            for (int i = 0; i < period; i++) {
                if (dp[startPoint + i]==false) termCnt++;
            }
            long middleCnt = (M - startPoint + 1) / period * termCnt;

            long endCnt = 0;
            for (int i = 0; i < (M - startPoint + 1) % period; i++) {
                if (dp[startPoint + i]==false) endCnt++;
            }

            return startCnt + middleCnt + endCnt;
        }

        private boolean checkPeriod(boolean[] dp, int start, int term) {
            int n = dp.length - 1;
            boolean[] base = new boolean[term];
            for (int i = 0; i < term; i++) {
                base[i] = dp[start + i];
            }
            int idx = start + term;
            while (idx + term - 1 <= n) {
                for (int i = 0; i < term; i++) {
                    if (dp[idx + i] != base[i]) {
                        return false;
                    }
                }
                idx += term;
            }
            return true;
        }
    }
}
