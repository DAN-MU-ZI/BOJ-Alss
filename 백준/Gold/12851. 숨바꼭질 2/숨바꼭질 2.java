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

        int[] bestScores = new int[100_001];
        int[] bestCnt = new int[100_001];
        Arrays.fill(bestScores, Integer.MAX_VALUE);
        Queue<Log> queue = new PriorityQueue<>();

        queue.add(new Log(N, 0));
        bestScores[N] = 0;
        bestCnt[N] = 1;

        while (!queue.isEmpty()) {
            Log l = queue.poll();

            int nextCnt = l.cnt + 1;
            int nextPos = l.pos + 1;
            if (0 <= nextPos && nextPos <= 100_000) {
                if (nextCnt < bestScores[nextPos]) {
                    bestScores[nextPos] = nextCnt;
                    bestCnt[nextPos] = 1;
                    queue.add(new Log(nextPos, nextCnt));
                }
                else if (nextCnt == bestScores[nextPos]) {
                    bestCnt[nextPos]++;
                    queue.add(new Log(nextPos, nextCnt));
                }
            }
            nextPos = l.pos - 1;
            if (0 <= nextPos && nextPos <= 100_000) {
                if (nextCnt < bestScores[nextPos]) {
                    bestScores[nextPos] = nextCnt;
                    bestCnt[nextPos] = 1;
                    queue.add(new Log(nextPos, nextCnt));
                }
                else if (nextCnt == bestScores[nextPos]) {
                    bestCnt[nextPos]++;
                    queue.add(new Log(nextPos, nextCnt));
                }
            }
            nextPos = l.pos * 2;
            if (0 <= nextPos && nextPos <= 100_000) {
                if (nextCnt < bestScores[nextPos]) {
                    bestScores[nextPos] = nextCnt;
                    bestCnt[nextPos] = 1;
                    queue.add(new Log(nextPos, nextCnt));
                }
                else if (nextCnt == bestScores[nextPos]) {
                    bestCnt[nextPos]++;
                    queue.add(new Log(nextPos, nextCnt));
                }
            }
        }


        System.out.println(bestScores[K]);
        System.out.println(bestCnt[K]);

    }
}