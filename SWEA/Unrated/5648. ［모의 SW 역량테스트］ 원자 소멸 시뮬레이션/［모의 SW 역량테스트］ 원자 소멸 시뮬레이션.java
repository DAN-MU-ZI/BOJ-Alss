import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int[][] deltas = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static Atom[] atoms;
    static Queue<int[]> pq;

    static class Atom {
        int x, y, d, k;

        public Atom(int x, int y, int d, int k) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.k = k;
        }
    }

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            atoms = new Atom[N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()) * 2;
                int y = Integer.parseInt(st.nextToken()) * 2;
                int d = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                atoms[i] = new Atom(x, y, d, k);
            }

            int answer = solve();
            sb.append(String.format("#%d %d\n", t, answer));
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }


    static int solve() {
        boolean[] deleted = new boolean[N];
        int[] dist = new int[N];

        generateQueue();

        int answer = 0;

        while (!pq.isEmpty()) {
            int currentDist = pq.peek()[2];

            Set<Integer> collisionSet = new HashSet<>();
            while (!pq.isEmpty() && pq.peek()[2] == currentDist) {
                int[] cur = pq.poll();
                int a = cur[0];
                int b = cur[1];

                if (dist[a] != 0 && dist[a] < currentDist) {
                    continue;
                }
                if (dist[b] != 0 && dist[b] < currentDist) {
                    continue;
                }

                if (!deleted[a]) {
                    deleted[a] = true;
                    dist[a] = currentDist;
                    collisionSet.add(a);
                }
                if (!deleted[b]) {
                    deleted[b] = true;
                    dist[b] = currentDist;
                    collisionSet.add(b);
                }
            }

            for (int idx : collisionSet) {
                answer += atoms[idx].k;
            }
        }

        return answer;
    }


    static void generateQueue() {
        pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                int time = calc(atoms[i], atoms[j]);
                if (time != INF) {
                    pq.add(new int[] { i, j, time });
                }
            }
        }
    }

    static int calc(Atom A, Atom B) {
        int dxA = deltas[A.d][0];
        int dyA = deltas[A.d][1];
        int dxB = deltas[B.d][0];
        int dyB = deltas[B.d][1];

        int diffDx = dxA - dxB;
        int diffDy = dyA - dyB;
        int diffX = B.x - A.x;
        int diffY = B.y - A.y;

        if (diffDx == 0 && diffDy == 0) {
            return INF;
        }

        if (diffDx == 0) {
            if (diffX != 0) return INF;
            if (diffDy == 0) return INF;
            int t = diffY / diffDy;
            return (t > 0) ? t : INF;
        }

        if (diffDy == 0) {
            if (diffY != 0) return INF;
            int t = diffX / diffDx;
            return (t > 0) ? t : INF;
        }

        int t1 = diffX / diffDx;
        int t2 = diffY / diffDy;

        if (t1 <= 0 || t2 <= 0) return INF;

        return (t1 == t2) ? t1 : INF;
    }
}
