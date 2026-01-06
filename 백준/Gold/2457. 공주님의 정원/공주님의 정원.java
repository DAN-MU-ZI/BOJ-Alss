import java.io.*;
import java.util.*;

public class Main {
    static class Flower implements Comparable<Flower> {
        int start;
        int end;

        public Flower(int sm, int sd, int em, int ed) {
            this.start = sm * 100 + sd;
            this.end = em * 100 + ed;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.start == o.start) {
                return o.end - this.end;
            }
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Flower> flowers = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());
            flowers.add(new Flower(sm, sd, em, ed));
        }

        Collections.sort(flowers);

        int current_end = 301;
        int count = 0;
        int idx = 0;

        while (current_end < 1201) {
            boolean found_candidate = false;
            int max_next_end = 0;

            while (idx < N && flowers.get(idx).start <= current_end) {
                if (flowers.get(idx).end > max_next_end) {
                    max_next_end = flowers.get(idx).end;
                    found_candidate = true;
                }
                idx++;
            }

            if (!found_candidate) {
                break;
            }

            current_end = max_next_end;
            count++;
        }

        if (current_end < 1201) {
            System.out.println(0);
        } else {
            System.out.println(count);
        }
    }
}