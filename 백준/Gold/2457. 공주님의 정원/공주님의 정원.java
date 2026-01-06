import java.io.*;
import java.util.*;

public class Main {
    private static final int TARGET_START = 301;
    private static final int TARGET_END = 1201;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] flowers = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());

            flowers[i][0] = sm * 100 + sd;
            flowers[i][1] = em * 100 + ed;
        }

        Arrays.sort(flowers, (o1, o2) -> {
            if (o1[0] == o2[0]) return o2[1] - o1[1];
            return o1[0] - o2[0];
        });

        int currentEnd = TARGET_START;
        int count = 0;
        int idx = 0;
        int maxEnd = 0;

        while (currentEnd < TARGET_END) {
            boolean isFound = false;
            maxEnd = currentEnd;

            while (idx < N && flowers[idx][0] <= currentEnd) {
                if (flowers[idx][1] > maxEnd) {
                    maxEnd = flowers[idx][1];
                    isFound = true;
                }
                idx++;
            }

            if (!isFound) break;

            currentEnd = maxEnd;
            count++;
        }

        if (currentEnd < TARGET_END) {
            System.out.println(0);
        } else {
            System.out.println(count);
        }
    }
}