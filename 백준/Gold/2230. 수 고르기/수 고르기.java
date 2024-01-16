import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N, M;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);

		int s, e;
		s = 0;
		e = 0;
		long answer = Long.MAX_VALUE;
		while (s <= e && e < N) {
			long diff = Math.abs(arr[e] - arr[s]);
			if (diff >= M) {
				answer = Math.min(answer, diff);
				s++;
			} else {
				e++;
			}

		}

		System.out.println(answer);
	}
}