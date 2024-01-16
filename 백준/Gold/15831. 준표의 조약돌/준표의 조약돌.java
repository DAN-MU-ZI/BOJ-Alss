import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N + 1][2];
		for (int i = 1; i < N + 1; i++) {
			int c = br.read();
			arr[i][0] = arr[i - 1][0];
			arr[i][1] = arr[i - 1][1];
			if (c == 'B')
				arr[i][0]++;
			else
				arr[i][1]++;
		}

		int s = 0, e = 0;
		int answer = 0;
		while (s <= N && e <= N) {
			int bCount = arr[e][0] - arr[s][0];
			int wCount = arr[e][1] - arr[s][1];
			if (bCount <= B) {
				if (W <= wCount)
					answer = Math.max(answer, e - s);
				e++;
			} else {
				s++;
			}
		}
		System.out.println(answer);
	}
}