import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		int p1 = 0;
		int p2 = 1;
		int p3 = 2;
		long minValue = Math.abs((long)arr[p1] + arr[p2] + arr[p3]);
		for (int i = 0; i < N - 2; i++) {
			int s = i + 1, e = N - 1;
			while (s < e) {
				long sum = (long)arr[i] + arr[s] + arr[e];
				long abs = Math.abs(sum);
				if (abs < minValue) {
					p1 = i;
					p2 = s;
					p3 = e;
					minValue = abs;
				}
				if (sum <= 0)
					s++;
				else
					e--;
			}
		}
		List<Integer> list = new java.util.ArrayList<>(List.of(arr[p1], arr[p2], arr[p3]));
		list.sort(Integer::compare);
		System.out.println(
			list.stream()
				.map(String::valueOf)
				.collect(Collectors.joining(" ")));
	}
}