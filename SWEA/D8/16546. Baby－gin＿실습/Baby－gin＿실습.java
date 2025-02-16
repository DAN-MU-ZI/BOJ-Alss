import java.io.*;
import java.util.*;

public class Solution {
	static int[] deck;
	static String cards;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			cards = br.readLine().trim();
			boolean answer = solve();
			sb.append(String.format("#%d %b\n", t, answer));
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static boolean solve() {
		deck = new int[10];
		for (char c : cards.toCharArray()) {
			deck[c - '0']++;
		}

		int run = 0, triplet = 0;

		for (int i = 0; i < 10; i++) {
			if (deck[i] >= 3) {
				run += deck[i] / 3;
				deck[i] %= 3;
			}
		}

		int idx = 0;
		while (idx < 8) {
			if (deck[idx] > 0 && deck[idx + 1] > 0 && deck[idx + 2] > 0) {
				triplet++;
				deck[idx]--;
				deck[idx + 1]--;
				deck[idx + 2]--;
			} else {
				idx++;
			}
		}

		return (run == 1 && triplet == 1) || (run == 2) || (triplet == 2);
	}
}