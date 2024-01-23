import java.io.IOException;

public class Main {
	static int d(int n) {
		int tmp = n;
		while (n > 0) {
			tmp += n % 10;
			n /= 10;
		}
		return tmp;
	}

	public static void main(String[] args) throws IOException {

		boolean[] table = new boolean[10001];
		for (int i = 1; i < 10001; i++) {
			int n = i;
			while (true) {
				n = d(n);
				if (n >= 10001) {
					break;
				}
				table[n] = true;
			}
		}
		for (int i = 1; i < 10001; i++) {
			if (!table[i]) {
				System.out.println(i);
			}
		}
	}
}
