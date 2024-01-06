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

		int[] flagTable = new int[1001];
		for (int i : table) {
			for (int j : table) {
				for (int k : table) {
					if (i+j+k<=1000)
						flagTable[i + j + k] = 1;
				}
			}
		}
		for (int j = 0; j < t; j++) {
			int n = Integer.parseInt(br.readLine());
			System.out.printf("%d\n", flagTable[n]);
		}
	}
}
