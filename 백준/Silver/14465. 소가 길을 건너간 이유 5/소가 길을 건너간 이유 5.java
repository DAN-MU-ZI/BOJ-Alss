import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		boolean[] safe = new boolean[N + 1];
		for (int i = 0; i < B; i++) {
			safe[Integer.parseInt(br.readLine())] = true;
		}

		int[] arr = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			arr[i] = arr[i - 1];
			if (safe[i])
				arr[i]++;
		}
		
		int answer = K;
		for (int i = K; i < N + 1; i++) {
			answer = Math.min(answer, (arr[i] - arr[i - K]));
		}
		System.out.println(answer);
	}
}