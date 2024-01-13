import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		boolean[] history = new boolean[26];
		int[] board = new int[n];
		int idx = 0;

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int term = Integer.parseInt(st.nextToken());
			int c = st.nextToken().charAt(0);
			idx = (idx + term) % n;

			if ((board[idx] != 0 && board[idx] != c)
				|| (board[idx] == 0 && history[c - 'A'])) {
				System.out.println("!");
				return;
			}
			board[idx] = c;
			history[c - 'A'] = true;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (board[idx] == 0) {
				sb.append("?");
			} else {
				sb.append((char)board[idx]);
			}
			idx = (--idx + n) % n;
		}
		System.out.println(sb);
	}
}
