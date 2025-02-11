import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int K = Integer.parseInt(br.readLine());

		String answer = Solution.solve(K);
		sb.append(answer);
		bw.write(sb.toString());

		bw.close();
		br.close();
	}

	static class Solution {
		static StringBuilder sb;
		static int N;

		public static String solve(int K) {
			N = K;

			sb = new StringBuilder(BigInteger.TWO.pow(N).subtract(BigInteger.ONE).toString()).append("\n");

			if (N > 20) {
				return sb.toString();
			}

			hanoi(1, 3, 2, K);

			return sb.toString();
		}

		static void hanoi(int s, int e, int a, int n) {
			if (n == 1) {
				sb.append(s + " " + e + "\n");
				return;
			}

			hanoi(s, a, e, n - 1);
			sb.append(s + " " + e + "\n");
			hanoi(a, e, s, n - 1);
		}
	}
}