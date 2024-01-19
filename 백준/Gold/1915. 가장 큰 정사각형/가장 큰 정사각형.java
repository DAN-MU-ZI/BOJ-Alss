import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] arr = new char[N + 1][M + 1];
		for (int i = 1; i < N + 1; i++) {
			String line = br.readLine();
			for (int j = 1; j < M + 1; j++) {
				arr[i][j] = line.charAt(j - 1);
			}
		}

		int[][] acc = new int[N + 1][M + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				if (arr[i][j] == '1') {
					acc[i][j] = Math.min(acc[i - 1][j - 1], Math.min(acc[i][j - 1], acc[i - 1][j])) + 1;
				} else {
					acc[i][j] = 0;
				}
			}
		}

		int answer = 0;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < M + 1; j++) {
				answer = Math.max(answer, acc[i][j]);
			}
		}
		System.out.println(answer*answer);
	}
}