import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arrA = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			arrA[i] = Integer.parseInt(st.nextToken()) + arrA[i - 1];
		}

		int[] ae = new int[(n * (n + 1)) / 2];
		int idx = 0;
		for (int i = 1; i < n + 1; i++) {
			for (int j = 0; j < i; j++) {
				ae[idx++] = arrA[i] - arrA[j];
			}
		}
		Arrays.sort(ae);

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arrB = new int[m + 1];
		for (int i = 1; i < m + 1; i++) {
			arrB[i] = Integer.parseInt(st.nextToken()) + arrB[i - 1];
		}

		int[] be = new int[(m * (m + 1)) / 2];
		idx = 0;
		for (int i = 1; i < m + 1; i++) {
			for (int j = 0; j < i; j++) {
				be[idx++] = arrB[i] - arrB[j];
			}
		}
		Arrays.sort(be);

		long answer = 0;
		idx = 0;
		while (idx < ae.length) {
			int target = t - ae[idx];
			int dstRange = getRange(be, target);

			if (dstRange == 0) {
				idx++;
				continue;
			}

			int srcRange = getRange(ae, ae[idx]);

			answer += (long)srcRange * dstRange;
			idx += srcRange;
		}
		System.out.println(answer);
	}

	static int getRange(int[] arr, int x) {
		return binaryUpperFind(arr, x) - binaryLowerFind(arr, x);
	}

	static int binaryLowerFind(int[] arr, int x) {
		int idx = arr.length;
		int s = 0, e = arr.length - 1;
		while (s <= e) {
			int m = (s + e) / 2;
			if (arr[m] < x)
				s = m + 1;
			else {
				e = m - 1;
				idx = m;
			}
		}
		return idx;
	}

	static int binaryUpperFind(int[] arr, int x) {
		int idx = arr.length;
		int s = 0, e = arr.length - 1;
		while (s <= e) {
			int m = (s + e) / 2;
			if (arr[m] <= x)
				s = m + 1;
			else {
				e = m - 1;
				idx = m;
			}
		}
		return idx;
	}
}