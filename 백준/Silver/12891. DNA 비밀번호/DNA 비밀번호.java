import java.io.*;
import java.util.*;

public class Main {
	static int S, P;
	static char[] str;
	static int[] rule;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		str = br.readLine().trim().toCharArray();

		rule = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			rule[i] = Integer.parseInt(st.nextToken());
		}

		bw.write(String.valueOf(solve()));
		bw.close();
		br.close();
	}

	static int solve() {
		int[] dict = new int[128];
		dict['A'] = 0;
		dict['C'] = 1;
		dict['G'] = 2;
		dict['T'] = 3;

		int answer = 0;

		int[] counter = new int[4];
		for (int i = 0; i < P - 1; i++) {
			counter[dict[str[i]]]++;
		}

		for (int i = P - 1; i < S; i++) {
			counter[dict[str[i]]]++;

			if (isSame(counter))
				answer++;

			counter[dict[str[i - P + 1]]]--;
		}

		return answer;
	}

	static boolean isSame(int[] counter) {
		for (int i = 0; i < 4; i++) {
			if (counter[i] < rule[i]) {
				return false;
			}
		}
		return true;
	}
}
