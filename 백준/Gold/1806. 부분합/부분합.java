import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N, S;
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N + 1];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int s, e;
		s = 0;
		e = 0;
		int answer = N + 1;
		long sum = arr[s];
		while (true) {
			if (sum >= S) {
				answer = Math.min(answer, e - s + 1);
				sum -= arr[s];
				s++;
			} else {
				e++;
				sum += arr[e];

				if (e == N)
					break;
			}
		}

		if (answer > N)
			System.out.println(0);
		else
			System.out.println(answer);
	}
}