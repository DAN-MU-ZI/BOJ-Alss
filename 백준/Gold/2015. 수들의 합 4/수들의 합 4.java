import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] sum = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
        }

        long answer = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int j = 1; j <= N; j++) {
            answer += map.getOrDefault(sum[j] - K, 0);
            map.put(sum[j], map.getOrDefault(sum[j], 0) + 1);
        }

        bw.write(String.valueOf(answer));
        bw.close();
        br.close();
    }
}
