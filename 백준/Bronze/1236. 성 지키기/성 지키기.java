import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] rowInfo = new int[n];
		int[] colInfo = new int[m];

		String[] map = new String[n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine();
			for (int j = 0; j < m; j++) {
				if (map[i].charAt(j) == 'X') {
					colInfo[j] = 1;
					rowInfo[i] = 1;
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (rowInfo[i] == 0 && colInfo[j] == 0) {
					answer++;
					rowInfo[i] = 1;
					colInfo[j] = 1;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (rowInfo[i] == 0) {
				answer++;
			}
		}
		for (int j = 0; j < m; j++) {
			if (colInfo[j] == 0) {
				answer++;
			}
		}
		System.out.printf("%d", answer);
	}
}
