import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] versions;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        Queue<int[]> pq = new PriorityQueue<>((s1, s2) -> {
            int cmp = s1[0] - s2[0];
            if (cmp != 0) return cmp;

            cmp = s1[1] - s2[1];
            if (cmp != 0) return cmp;

            return s1[2] - s2[2];
        });


        N = Integer.parseInt(br.readLine());
        versions = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            pq.add(new int[]{num, i, versions[i]});
        }
        
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if (a == 1) {
                int idx = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                versions[idx]++;
                int version = versions[idx];
                pq.add(new int[] {v, idx, version});
            } else if (a == 2) {
                while (true) {
                    int[] node = pq.peek();
                    int num = node[0];
                    int idx = node[1];
                    int version = node[2];

                    if (versions[idx] > version) {
                        pq.poll();
                    } else {
                        break;
                    }
                }

                sb.append(pq.peek()[1] + "\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}