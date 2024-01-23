import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		boolean[] broken = new boolean[10];

		if (M > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int num = Integer.parseInt(st.nextToken());
				broken[num] = true;
			}
		}

		int answer = Math.abs(N - 100);
		for (int i = 0; i <= 999999; i++) {
			String str = String.valueOf(i);

			boolean isBroken = false;
			for (char c : str.toCharArray()) {
				if (broken[c - '0']) {
					isBroken = true;
					break;
				}
			}

			if (!isBroken) {
				int min = Math.abs(N - i) + str.length();
				answer = Math.min(answer, min);
			}
		}
		System.out.println(answer);
	}

}