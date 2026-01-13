import java.io.*;
import java.util.*;

public class Main {
    static int MAX = 100_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            System.out.println(N - K);
            System.out.println(1);
            return;
        }

        int[] time = new int[MAX + 1];
        int[] count = new int[MAX + 1];
        Arrays.fill(count, Integer.MAX_VALUE);

        Queue<Integer> q = new ArrayDeque<>();

        q.add(N);
        time[N] = 1;
        count[N] = 1;

        while (!q.isEmpty()) {
            int current = q.poll();

            if (time[K] != 0 && time[current] > time[K]) {
                break;
            }

            int[] nextPositions = {current - 1, current + 1, current * 2};

            for (int next : nextPositions) {
                if (next < 0 || next > MAX) continue;

                if (time[next] == 0) {
                    time[next] = time[current] + 1;
                    count[next] = count[current];
                    q.add(next);
                }
                else if (time[next] == time[current] + 1) {
                    count[next] += count[current];
                }
            }
        }

        System.out.println(time[K] - 1);
        System.out.println(count[K]);

    }
}