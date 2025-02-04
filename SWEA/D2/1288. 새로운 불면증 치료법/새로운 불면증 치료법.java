import java.io.*;
import java.util.*;

class Solution
{
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int answer = solve(N);
            sb.append(String.format("#%d %d\n", t, answer));
        }

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

    private static int solve(int N) {
        int mask = 0;
        int step = 0;
        int num = 0;
        while(true) {
            step++;
            num += N;

            if (num % 10 == 0) 
                mask |= 1;

            int tmp = num;
            while(tmp > 0) {
                mask |= 1 << (tmp % 10);
                tmp /= 10;
            }

            if (mask == (1 << 10) - 1) {
                break;
            }
        }

        return num;
    }
}