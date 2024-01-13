import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int X = Integer.parseInt(br.readLine());
		int cutline = X / 100 * 5;
		int N = Integer.parseInt(br.readLine());
		int[][] scores = new int[N][15];
		int[] members = new int[26];
		boolean[] visited = new boolean[26];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int c = st.nextToken().charAt(0);
			int vote = Integer.parseInt(st.nextToken());
			if (vote < cutline) {
				continue;
			}
			for (int j = 1; j <= 14; j++) {
				scores[i][j] = vote / j;
			}
			members[c - 'A'] = i;
			visited[c - 'A'] = true;
		}

		int[] chips = new int[N];
		for (int k = 0; k < 14; k++) {
			int nthMaxvalue = 0;
			int x = 0;
			int y = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 1; j <= 14; j++) {
					if (nthMaxvalue < scores[i][j]) {
						nthMaxvalue = scores[i][j];
						x = i;
						y = j;
					}
				}
			}
			scores[x][y] = 0;
			chips[x]++;
		}

		StringBuilder sb = new StringBuilder();
		for (char c = 'A'; c <= 'Z'; c++) {
			if (visited[c - 'A']) {
				sb.append(c).append(" ").append(chips[members[c - 'A']]).append("\n");
			}
		}
		System.out.println(sb);
	}
}
