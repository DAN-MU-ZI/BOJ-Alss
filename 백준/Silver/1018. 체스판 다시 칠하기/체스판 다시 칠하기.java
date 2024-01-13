import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int minPaint(char[][] map, int x, int y) {
		int w1 = 0;
		int b1 = 0;

		int w2 = 0;
		int b2 = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ((i + j) % 2 == 0) {
					if (map[x + i][y + j] != 'W') {
						w1++;
					} else {
						b2++;
					}
				} else {
					if (map[x + i][y + j] != 'B') {
						b1++;
					} else {
						w2++;
					}
				}
			}
		}
		return Math.min(w1 + b1, w2 + b2);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n, m;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		char[][] map = new char[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int answer = 32;
		for (int i = 0; i <= n - 8; i++) {
			for (int j = 0; j <= m - 8; j++) {
				answer = Math.min(answer, minPaint(map, i, j));
			}
		}
		System.out.println(answer);
	}
}
