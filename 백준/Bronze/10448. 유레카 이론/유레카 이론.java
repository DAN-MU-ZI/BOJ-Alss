import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.util.concurrent.CountDownLatch;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		int[] table = new int[44];
		int s = 0;
		for (int i = 1; i < 1000; i++) {
			if (s + i > 1000) {
				break;
			}
			s = s + i;
			table[i - 1] = s;
		}
		for (int j = 0; j < t; j++) {
			System.out.printf("%d\n", (func(br, table)));
		}
	}

	private static int func(BufferedReader br, int[] table) throws IOException {
		int n = Integer.parseInt(br.readLine());
		for (int j : table) {
			for (int k : table) {
				for (int value : table) {
					if (j + k + value == n) {
						return 1;
					}
				}
			}
		}
		return 0;
	}
}
