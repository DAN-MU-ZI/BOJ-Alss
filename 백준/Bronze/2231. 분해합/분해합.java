import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());

		for (int i = 1; i <= n; i++) {
			int tmp = i;
			int sum = tmp;

			while (tmp > 0) {
				sum += tmp % 10;
				tmp /= 10;
			}

			if (n == sum) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(0);
	}
}
