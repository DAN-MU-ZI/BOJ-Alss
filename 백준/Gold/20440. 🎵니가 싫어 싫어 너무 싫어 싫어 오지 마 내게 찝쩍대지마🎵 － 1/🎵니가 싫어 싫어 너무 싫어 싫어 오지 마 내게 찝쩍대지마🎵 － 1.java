import java.io.*;
import java.util.*;

public class Main {
    static class Mosquito implements Comparable<Mosquito> {
        long time;
        int delta;
        Mosquito(long time, int delta) {
            this.time = time;
            this.delta = delta;
        }
        @Override
        public int compareTo(Mosquito o) {
            if (this.time == o.time) return this.delta - o.delta;
            return Long.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Mosquito> events = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long enter = Long.parseLong(st.nextToken());
            long exit = Long.parseLong(st.nextToken());
            events.add(new Mosquito(enter, +1));
            events.add(new Mosquito(exit, -1));
        }

        Collections.sort(events);

        long maxCount = 0, cur = 0;
        long bestStart = -1, bestEnd = -1;

        for (int i = 0; i < events.size(); i++) {
            cur += events.get(i).delta;
            long now = events.get(i).time;
            long next = (i + 1 < events.size()) ? events.get(i + 1).time : now;

            if (cur > maxCount) {
                maxCount = cur;
                bestStart = now;
                bestEnd = next;
            } else if (cur == maxCount && maxCount > 0 && bestEnd == now) {
                bestEnd = next;
            }
        }

        System.out.println(maxCount);
        System.out.println(bestStart + " " + bestEnd);
    }
}
