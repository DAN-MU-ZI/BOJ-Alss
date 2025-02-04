import java.io.*;
import java.util.*;

class Solution
{
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            String answer = solve(N, M);
            sb.append(String.format("#%d %s\n", t, answer));
        }

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

    private static String solve(int N, int M) {
        return (M & ((1 << N) - 1)) == ((1 << N) - 1) ? "ON" : "OFF";
    }
}