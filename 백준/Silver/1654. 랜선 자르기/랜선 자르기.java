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
		int m = Integer.parseInt(st.nextToken());

		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		long s = 1;
		long e = Arrays.stream(arr).max().getAsLong();
		while (s <= e) {
			long mid = (s + e) / 2;
			long sum = 0;
			for (long i : arr) {
				sum += i / mid;
			}

			if (sum >= m)
				s = mid + 1;
			else
				e = mid - 1;
		}
		System.out.println(e);
	}
}