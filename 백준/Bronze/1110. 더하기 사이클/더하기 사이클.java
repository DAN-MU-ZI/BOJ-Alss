import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int sum(int n) {
		int save = n;
		int tmp = 0;
		while (n > 0) {
			tmp += n % 10;
			n /= 10;
		}

		return Integer.parseInt(String.valueOf(save % 10) + tmp % 10);
	}

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());

		int save = n;
		int cnt = 0;
		do {
			n = sum(n);
			cnt++;
		} while (n != save);

		System.out.println(cnt);
	}
}
