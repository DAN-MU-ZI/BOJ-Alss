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

        int[][] deltas = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        HashMap<Integer, Integer> costs = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();

        costs.put(init, 0);
        queue.add(init);

        while (!queue.isEmpty()) {
            int hash = queue.poll();
            int cost = costs.get(hash);

            int addr = findBlankIndex(hash);
            int r = addr / 3;
            int c = addr % 3;

            if (hash == answer) {
                return cost;
            }

            for (int[] delta : deltas) {
                int nr = r + delta[0];
                int nc = c + delta[1];

                if (!isValid(nr, nc))
                    continue;

                int nextHash = swapPositions(hash, r, c, nr, nc);
                int nextCost = cost + 1;

                if (isValid(nr, nc)) {
                    if (costs.containsKey(nextHash)) {
                        if (nextCost < costs.get(nextHash)) {
                            costs.put(nextHash, nextCost);
                            queue.add(nextHash);
                        }
                    } else {
                        costs.put(nextHash, nextCost);
                        queue.add(nextHash);
                    }
                }
            }
        }
        return -1;
    }

    static final int[] POWERS = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };

    static int swapPositions(int hash, int r, int c, int nr, int nc) {
        int fromDiv = POWERS[8 - (r * 3 + c)];
        int toDiv = POWERS[8 - (nr * 3 + nc)];

        int from = hash / fromDiv % 10;
        int to = hash / toDiv % 10;

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

    static int findBlankIndex(int hash) {
        for (int i = 0; i < 9; i++) {
            if (hash % 10 == 0) {
                return 8 - i;
            }
            hash /= 10;
        }
        return 0;
    }
}