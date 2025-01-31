import java.io.*;
import java.util.*;

class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
		StringBuilder sb = new StringBuilder();

        int N,M,K;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] fireBalls  = new int[M][5];
        int r,c,m,s,d;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            m = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            fireBalls[i] = new int[]{r,c,m,s,d};
        }
        Solution solution = new Solution(N,M,K,fireBalls);
        sb.append(solution.solve());

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

    

    private static class Solution {
        int N,M,K;
        List<FireBall> fireBalls = new ArrayList<>();
        int[] dr = {-1, -1, 0, 1, 1,  1,  0, -1};
        int[] dc = { 0,  1, 1, 1, 0, -1, -1, -1};

        public Solution(int N, int M, int K, int[][] fireBalls) {
            this.N = N;
            this.M = M;
            this.K = K;

            for (int i = 0; i < M; i++) {
                int[] info = fireBalls[i];
                this.fireBalls.add(new FireBall(info));
            }
        }

        class FireBall{
            int pos,m,d,s;
            
            public FireBall (int[] info) {
                this.pos = info[0] * N + info[1];
                this.m = info[2];
                this.s = info[3];
                this.d = info[4];
            }

            public FireBall (int pos, int m, int s, int d) {
                this.pos = pos;
                this.m = m;
                this.s = s;
                this.d = d;
            }

            public Integer move() {
                int nr = pos / N;
                int nc = pos % N;
                for (int i = 0; i < s; i++){
                    nr = (nr + dr[d] + N) % N;
                    nc = (nc + dc[d] + N) % N;
                }
                this.pos = nr * N + nc;
                return nr * N + nc;
            }
        }

        public int solve() {
            while (K-- > 0) {
                Map<Integer, List<FireBall>> map = new HashMap<>();
                
                for(FireBall fireBall: this.fireBalls) {
                    Integer pos = fireBall.move();
                    map.computeIfAbsent(pos, k -> new ArrayList<>()).add(fireBall);
                }
                
                List<FireBall> newFireBalls = new ArrayList<>();
                for (Integer key: map.keySet()) {
                    List<FireBall> fireBalls = map.get(key);
                    if (fireBalls.size() >= 2) {
                        List<FireBall> splitedFireBall = split(fireBalls);
                        if (splitedFireBall != null) {
                            newFireBalls.addAll(splitedFireBall);
                        }
                    } else {
                        newFireBalls.addAll(fireBalls);
                    }
                }
                fireBalls = newFireBalls;
            }

            int answer = 0;
            for (FireBall fireBall: fireBalls) {
                answer += fireBall.m;
            }
            return answer;
        }

        private List<FireBall> split(List<FireBall> fireBalls) {
            int pos, m, s, d;
            pos = 0;
            m = 0;
            s = 0;

            boolean isOdd = false;
            boolean isEven = false;
            for (FireBall fireBall: fireBalls) {
                pos = fireBall.pos;
                m += fireBall.m;
                s += fireBall.s;
                if (fireBall.d % 2 == 1) {
                    isOdd = true;
                } else {
                    isEven = true;
                }
            }

            if (m / 5 == 0) return null;

            List<FireBall> newFireBalls = new ArrayList<>();
            if (isOdd ^ isEven) {
                newFireBalls.add(new FireBall(pos, m / 5, s / fireBalls.size(), 0));
                newFireBalls.add(new FireBall(pos, m / 5, s / fireBalls.size(), 2));
                newFireBalls.add(new FireBall(pos, m / 5, s / fireBalls.size(), 4));
                newFireBalls.add(new FireBall(pos, m / 5, s / fireBalls.size(), 6));
            } else {
                newFireBalls.add(new FireBall(pos, m / 5, s / fireBalls.size(), 1));
                newFireBalls.add(new FireBall(pos, m / 5, s / fireBalls.size(), 3));
                newFireBalls.add(new FireBall(pos, m / 5, s / fireBalls.size(), 5));
                newFireBalls.add(new FireBall(pos, m / 5, s / fireBalls.size(), 7));
            }

            return newFireBalls;
        }
    }
}
