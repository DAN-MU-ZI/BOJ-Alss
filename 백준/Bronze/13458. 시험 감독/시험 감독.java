import java.io.*;
import java.util.*;

public class Main {
	static int N, B, C;
	static int[] A;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		A = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		long answer = solve();
		bw.write(String.valueOf(answer));
		bw.close();
		br.close();
	}

	static long solve() {
		long answer = 0;
		for (int a : A) {
			answer++;
			a = Math.max(0, a - B);
			answer += a / C;
			if (a % C > 0) {
				answer++;
			}
		}
		return answer;
	}
}
