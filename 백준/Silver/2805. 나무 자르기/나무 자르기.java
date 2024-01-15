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

		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int s = 0;
		int e = Arrays.stream(arr).max().getAsInt();
		while (s <= e) {
			int mid = (s + e) / 2;
			long sum = 0;
			for (int i = 0; i < n; i++) {
				if (arr[i] >= mid)
					sum += arr[i] - mid;
			}
			if (sum >= m) {
				s = mid + 1;
			} else
				e = mid - 1;
		}
		System.out.println(e);
	}
}