import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.Deque;

public class Main {
    static int N, K;
    static int[][] stateMap;
    static Deque<Integer>[][] map;
    static Piece[] pieces;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        stateMap = new int[N][N];
        map = new ArrayDeque[N][N];
        pieces = new Piece[K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int state = Integer.parseInt(st.nextToken());
                stateMap[i][j] = state;
                map[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            Piece p = new Piece(r, c, d);
            pieces[i] = p;
            map[r][c].add(i);
        }
        System.out.println(solve());
    }

    public static int solve() {
        int turn = 0;
        // printMap();
        while (++turn <= 1000) {
            int result = processTurn();
            // if (turn < 10)
            // printMap();
            if (result >= 4)
                return turn;
        }

        return -1;
    }

    static int[][] directions = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

    static void printMap() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j].size() + " ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        System.out.println(sb);
    }

    public static int processTurn() {
        int stk = 0;
        for (int i = 1; i <= K; i++) {
            Piece piece = pieces[i];
            if (!piece.isCandidate) {
                continue;
            }

            int r = piece.r;
            int c = piece.c;
            int d = piece.d;

            int[] deltas = directions[d];

            int nr = r + deltas[0];
            int nc = c + deltas[1];

            int isSuccesed = move(r, c, nr, nc);
            if (isSuccesed >= 0) {
                stk = Math.max(stk, isSuccesed);
            } else {
                int nd = revDirection(d);

                piece.d = nd;
                d = nd;

                deltas = directions[d];
                nr = r + deltas[0];
                nc = c + deltas[1];

                stk = Math.max(stk, move(r, c, nr, nc));
            }
        }
        return stk;
    }

    static int revDirection(int d) {
        if (d == 0) {
            return 1;
        } else if (d == 1) {
            return 0;
        } else if (d == 2) {
            return 3;
        } else if (d == 3) {
            return 2;
        }
        return -1;
    }

    static int move(int srcR, int srcC, int dstR, int dstC) {
        if (!isValid(dstR, dstC))
            return -1;

        int state = stateMap[dstR][dstC];

        if (state == 0) {
            if (map[dstR][dstC].isEmpty()) {
                return swap(srcR, srcC, dstR, dstC);
            } else {
                return addForward(srcR, srcC, dstR, dstC);
            }
        } else if (state == 1) {
            return addBackward(srcR, srcC, dstR, dstC);
        } else if (state == 2) {
            return -1;
        }
        return -1;
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static int addBackward(int srcR, int srcC, int dstR, int dstC) {
        Deque<Integer> tmp = map[srcR][srcC];
        while (!tmp.isEmpty()) {
            int t = tmp.pollLast();
            map[dstR][dstC].add(t);

            Piece p = pieces[t];
            p.r = dstR;
            p.c = dstC;
            p.isCandidate = false;
        }
        Piece head = pieces[map[dstR][dstC].peek()];
        head.isCandidate = true;
        return map[dstR][dstC].size();
    }

    static int addForward(int srcR, int srcC, int dstR, int dstC) {
        Deque<Integer> tmp = map[srcR][srcC];
        while (!tmp.isEmpty()) {
            int t = tmp.pollFirst();
            map[dstR][dstC].add(t);

            Piece p = pieces[t];
            p.r = dstR;
            p.c = dstC;
            p.isCandidate = false;
        }
        return map[dstR][dstC].size();
    }

    static int swap(int srcR, int srcC, int dstR, int dstC) {
        Deque<Integer> tmp = map[srcR][srcC];
        map[srcR][srcC] = map[dstR][dstC];
        map[dstR][dstC] = tmp;

        for (int i : tmp) {
            Piece p = pieces[i];
            p.r = dstR;
            p.c = dstC;
        }
        return map[dstR][dstC].size();
    }
}

class Piece {
    int r, c, d;
    boolean isCandidate;

    public Piece(int _r, int _c, int _d) {
        r = _r;
        c = _c;
        d = _d;
        isCandidate = true;
    }
}