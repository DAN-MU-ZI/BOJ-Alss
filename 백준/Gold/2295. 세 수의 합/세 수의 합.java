import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static boolean binaryFind(int num, int[] arr, int end) {
		int start = 0;
		while (true) {
			if (start > end)
				return false;

			int mid = (start + end) / 2;

			if (arr[mid] == num)
				return true;
			else if (arr[mid] < num) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);

		int[] sqrtArr = new int[n * (n + 1) / 2];
		int idx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				sqrtArr[idx++] = arr[i] + arr[j];
			}
		}
		Arrays.sort(sqrtArr);

		int answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int num = arr[j] - arr[i];
				if (binaryFind(num, sqrtArr, sqrtArr.length)) {
					answer = Math.max(answer, arr[j]);
				}
			}
		}
		System.out.println(answer);
	}
}