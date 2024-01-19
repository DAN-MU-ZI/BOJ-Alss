import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());

		int s = 1;
		int e = K;
		while (s <= e) {
			int mid = (s + e) / 2;
			int count = 0;
			for (int i = 1; i < N + 1; i++) {
				int div = mid / i;
				count += Math.min(div, N);
			}

			if (count >= K)
				e = mid - 1;
			else
				s = mid + 1;
		}
		System.out.println(s);
	}
}