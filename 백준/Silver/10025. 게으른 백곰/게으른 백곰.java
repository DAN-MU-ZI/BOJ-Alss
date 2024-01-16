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

		int length = 1000001;
		int[] arr = new int[length];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			arr[x] += g;
		}

		int sum = 0;
		for (int i = 0; i < Math.min(length, 2 * K + 1); i++) {
			sum += arr[i];
		}

		int answer = sum;
		for (int i = 2 * K + 1; i < length; i++) {
			int prevIdx = i - 2 * K - 1;
			sum -= arr[prevIdx];
			sum += arr[i];
			answer = Math.max(answer, sum);
		}
		System.out.println(answer);
	}
}