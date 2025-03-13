import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int[][] arr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = solve(arr);

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    static int solve(int[][] arr) {
        int answer = 123456780;
        int init = getHash(arr);
        // System.out.println(init);
        // System.out.println(getAddress(init));

        // System.out.println(apply(init, 0, 1, 0, 2));
        // System.out.println(apply(init, 0, 1, 0, 1));
        // System.out.println(apply(init, 0, 1, 0, 0));

        int[][] deltas = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        HashMap<Integer, Integer> costs = new HashMap<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((s1, s2) -> s1[1] - s2[1]);

        costs.put(init, 0);
        pq.add(new int[] { init, 0 });

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int hash = cur[0];
            int cost = cur[1];

            int addr = getAddress(hash);
            int r = addr / 3;
            int c = addr % 3;

            if (hash == answer) {
                return cost;
            }

            for (int[] delta : deltas) {
                int nr = r + delta[0];
                int nc = c + delta[1];
                int nextHash = apply(hash, r, c, nr, nc);
                int nextCost = cost + 1;

                if (isValid(nr, nc)) {
                    if (costs.containsKey(nextHash)) {
                        if (nextCost < costs.get(nextHash)) {
                            costs.put(nextHash, nextCost);
                            pq.add(new int[] { nextHash, nextCost });
                        }
                    } else {
                        costs.put(nextHash, nextCost);
                        pq.add(new int[] { nextHash, nextCost });
                    }
                }
            }
        }
        return -1;
    }

    static int apply(int hash, int r, int c, int nr, int nc) {
        int fromDiv = (int) Math.pow(10, (8 - (r * 3 + c)));
        int toDiv = (int) Math.pow(10, (8 - (nr * 3 + nc)));
        // System.out.println(8 - (r * 3 + c) + ", " + (8 - (nr * 3 + nc)));

        int from = fromDiv == 0 ? hash % 10 : hash / fromDiv % 10;
        int to = toDiv == 0 ? hash % 10 : hash / toDiv % 10;

        // System.out.println(from + ", " + to);
        hash -= from * fromDiv;
        hash -= to * toDiv;

        hash += fromDiv * to;
        hash += toDiv * from;
        return hash;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < 3 && 0 <= c && c < 3;
    }

    static int getHash(int[][] arr) {
        int hash = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                hash *= 10;
                hash += arr[i][j];
            }
        }
        return hash;
    }

    static int getAddress(int hash) {
        for (int i = 0; i < 9; i++) {
            if (hash % 10 == 0) {
                return 8 - i;
            }
            hash /= 10;
        }
        return 0;
    }
}