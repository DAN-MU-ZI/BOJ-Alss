import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		String[] arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().strip();
		}

		int answer = solution(N, K, arr);

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}

	public static int initializeBitmask() {
		int bitmask = 0;
		for (char c : "antatica".toCharArray()) {
			bitmask |= 1 << (c - 'a');
		}
		return bitmask;
	}

	public static int solution(int N, int K, String[] arr) {
		int bitmask = initializeBitmask();

		int[] wordCounter = new int[N];
		for (int i = 0; i < N; i++) {
			for (char c : arr[i].toCharArray()) {
				wordCounter[i] |= 1 << (c - 'a');
			}
		}

		int answer = 0;
		for (int mask = bitmask; mask < (1 << 26); mask++) {
			if (Integer.bitCount(mask) == K) {
				int count = 0;
				for (int word : wordCounter) {
					if ((mask & word) == word)
						count++;
				}
				answer = Math.max(answer, count);
			}
		}

		return answer;
	}
}