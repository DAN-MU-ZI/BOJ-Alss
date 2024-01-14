import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// static boolean binaryFind(int num, int[] arr, int end) {
	// 	int start = 0;
	// 	while (true) {
	// 		if (start > end)
	// 			return false;
	//
	// 		int mid = (start + end) / 2;
	//
	// 		if (arr[mid] == num)
	// 			return true;
	// 		else if (arr[mid] < num) {
	// 			start = mid + 1;
	// 		} else {
	// 			end = mid - 1;
	// 		}
	// 	}
	// }

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		int s, e;
		s = 0;
		e = n - 1;

		int ns, ne;
		ns = s;
		ne = e;

		int answer = Math.abs(arr[s] + arr[e]);
		while (s < e) {
			int cur = arr[s] + arr[e];
			if (answer > Math.abs(cur)) {
				ns = s;
				ne = e;
				answer = Math.abs(cur);
			}

			if (cur < 0)
				s++;
			else if (cur == 0)
				break;
			else
				e--;
		}
		System.out.printf("%d %d", arr[ns], arr[ne]);
	}
}