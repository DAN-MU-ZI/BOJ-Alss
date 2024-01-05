import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int x = Integer.parseInt(br.readLine());

		Arrays.sort(arr);

		int answer = 0;
		int s = 0;
		int e = n - 1;

		while (s < e) {
			if ((arr[s] + arr[e]) > x) {
				e--;
			} else if ((arr[s] + arr[e]) < x) {
				s++;
			} else {
				s++;
				e--;
				answer++;
			}
		}
		System.out.printf("%d", answer);
	}
}
