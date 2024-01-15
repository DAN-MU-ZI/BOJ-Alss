import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		System.out.println(func(n));
	}

	private static long func(long n) {
		if (n == 0)
			return 0;
		long s = 1, e = 1L << 32, res = -1;
		while (s <= e) {
			long m = (s + e) / 2;
			if (Math.pow(m, 2) >= n) {
				e = m - 1;
				res = m;
			} else
				s = m + 1;
		}
		return res;
	}
}