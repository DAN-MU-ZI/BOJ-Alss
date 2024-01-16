import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n, m;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		long answer = 0;
		int s = 0;
		int e = 0;
		long sum = 0;
		while (true) {
			if (sum >= m)
				sum -= arr[s++];
			else if (e == n)
				break;
			else
				sum += arr[e++];

			if (sum == m)
				answer++;
		}

		System.out.println(answer);
	}
}