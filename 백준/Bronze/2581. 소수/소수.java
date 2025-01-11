import java.io.*;
import java.util.*;

class Main {
	static int SIZE;
	static boolean[] filter;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder answer = new StringBuilder();
		
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		SIZE = N;
		filter = new boolean[SIZE + 1];

		Arrays.fill(filter, true);
		filter[0] = false;
        filter[1] = false;

		for (int i = 2; i <= SIZE; i++) {
			if (!filter[i]) continue;

			for (int j = i * 2; j <= SIZE; j+=i) {
				filter[j] = false;
			}
		}

		long sum = 0;
		int min = Integer.MAX_VALUE;
		for (int i = M; i <= N; i++) {
			if (filter[i]) {
				sum += i;
				min = Math.min(min, i);
			}
		}

		if (sum == 0) {
			bw.write(String.valueOf(-1));
		} else {
			bw.write(String.valueOf(sum) + "\n");
			bw.write(String.valueOf(min));
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
