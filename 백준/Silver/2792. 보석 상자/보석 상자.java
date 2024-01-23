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
		int n, m;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int[] arr = new int[m];
		for (int i = 0; i < m; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int max = Arrays.stream(arr).max().getAsInt();
		int s = 1;
		int e = max;
		while (s <= e) {
			int mid = (s + e) / 2;

			int sum = 0;
			for (int a : arr) {
				sum += a / mid;
				if (a % mid > 0)
					sum++;
			}

			if (sum <= n) {
				e = mid - 1;
			} else
				s = mid + 1;
		}
		System.out.println(s);
	}
}