import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			arr[i] = num;
		}
		Arrays.sort(arr);

		int s = 1;
		int e = arr[n - 1] - arr[0];
		int answer = 0;
		while (s <= e) {
			int mid = (s + e) / 2;

			int cnt = 1;
			int prev = arr[0];
			for (int i = 1; i < n; i++) {
				if (arr[i] - prev >= mid) {
					cnt++;
					prev = arr[i];
				}
			}

			if (cnt >= c) {
				s = mid + 1;
				answer = mid;
			} else
				e = mid - 1;
		}
		System.out.println(answer);
	}
}