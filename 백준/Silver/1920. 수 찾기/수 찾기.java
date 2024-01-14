import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int binaryFind(int num, int[] arr, int start, int end) {
		if (start > end)
			return 0;

		int mid = (start + end) / 2;

		if (arr[mid] == num)
			return 1;
		else if (arr[mid] < num) {
			start = mid + 1;
		} else {
			end = mid - 1;
		}
		return binaryFind(num, arr, start, end);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st;

		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for (int j = 0; j < m; j++) {
			int num = Integer.parseInt(st.nextToken());
			sb.append(binaryFind(num, arr, 0, n - 1)).append("\n");
		}
		System.out.println(sb);
	}
}