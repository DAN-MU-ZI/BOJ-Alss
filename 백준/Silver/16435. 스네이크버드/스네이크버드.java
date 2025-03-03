import java.io.*;
import java.util.*;

public class Main {
	static int N, L;
	static int[] h;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		h = new int[10001];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			h[num]++;
		}

		bw.write(String.valueOf(solve()));
		bw.close();
		br.close();
	}

	static int solve() {
		for (int i = 0; i <= L; i++) {
			L += h[i];
		}

		for (int i = L; i <= 10000; i++) {
			if (i <= L) {
				L += h[i];
			} else {
				return L;
			}
		}

		return L;
	}
}
