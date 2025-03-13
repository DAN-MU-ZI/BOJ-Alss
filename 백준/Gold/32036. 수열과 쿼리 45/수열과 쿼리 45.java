import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int q = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> higher = new PriorityQueue<>();

        long sumLower = 0;
        long sumHigher = 0;

        long sumY = 0;

        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int com = Integer.parseInt(st.nextToken());
            if (com == 1) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                sumY += y;

                if (lower.isEmpty() || x <= lower.peek()) {
                    lower.offer(x);
                    sumLower += x;
                } else {
                    higher.offer(x);
                    sumHigher += x;
                }

                if (lower.size() < higher.size()) {
                    int moved = higher.poll();
                    sumHigher -= moved;
                    lower.offer(moved);
                    sumLower += moved;
                } else if (lower.size() > higher.size() + 1) {
                    int moved = lower.poll();
                    sumLower -= moved;
                    higher.offer(moved);
                    sumHigher += moved;
                }
            } else if (com == 2) {
                int median = lower.peek();
                int countLower = lower.size();
                int countHigher = higher.size();

                long cost = (long) median * countLower - sumLower + sumHigher - (long) median * countHigher;
                long ans = sumY + cost;
                sb.append(median + " " + ans + "\n");
            }
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}