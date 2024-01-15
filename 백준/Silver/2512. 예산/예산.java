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
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
		}

		int m = Integer.parseInt(br.readLine());

		int s = 0;
		int e = Arrays.stream(arr).max().getAsInt();
		while (s <= e) {
			int mid = (s + e) / 2;
			int sum = 0;
			for (int a : arr) {
				sum += Math.min(mid, a);
			}

			if (m >= sum) {
				s = mid + 1;
			} else
				e = mid - 1;
		}
		System.out.println(e);
	}
}