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
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			arr[i] = num;
		}

		int s = 0;
		int e = Arrays.stream(arr).sum();
		int answer = 0;
		while (s <= e) {
			int mid = (s + e) / 2;

			if (isOver(arr, mid, m)) {
				e = mid - 1;
				answer = mid;
			} else
				s = mid + 1;
		}
		System.out.println(answer);
	}

	private static boolean isOver(int[] arr, int mid, int m) {
		int count = 1;
		int stk = mid;
		for (int a : arr) {
			if (a > mid)
				return false;
			if (stk < a) {
				if (count == m)
					return false;
				count++;
				stk = mid;
			}
			stk -= a;
		}
		return true;
	}
}