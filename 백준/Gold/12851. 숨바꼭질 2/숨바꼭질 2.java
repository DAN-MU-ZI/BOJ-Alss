import java.io.*;
import java.util.*;

public class Main {
    public static class Log implements Comparable<Log> {
        int pos;
        int cnt;

        public Log(int _pos, int _cnt) {
            pos = _pos;
            cnt = _cnt;
        }

        public int compareTo(Log h) {
            return cnt - h.cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            System.out.println(N - K);
            System.out.println(1);
            return;
        }

        int[] bestScores = new int[100_001];
        int[] bestCnt = new int[100_001];
        Arrays.fill(bestScores, Integer.MAX_VALUE);

        Queue<Log> queue = new PriorityQueue<>();

        queue.add(new Log(N, 0));
        bestScores[N] = 0;
        bestCnt[N] = 1;

        while (!queue.isEmpty()) {
            Log l = queue.poll();

            if (l.cnt > bestScores[l.pos]) continue;

            int[] nextPositions = {l.pos + 1, l.pos - 1, l.pos * 2};

            for (int nextPos : nextPositions) {
                if (nextPos < 0 || nextPos > 100_000) continue;

                int nextTime = l.cnt + 1;

                if (nextTime < bestScores[nextPos]) {
                    bestScores[nextPos] = nextTime;
                    bestCnt[nextPos] = bestCnt[l.pos];
                    queue.add(new Log(nextPos, nextTime));
                } else if (nextTime == bestScores[nextPos]) {
                    bestCnt[nextPos] += bestCnt[l.pos];
                }
            }
        }

        System.out.println(bestScores[K]);
        System.out.println(bestCnt[K]);

    }
}