import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.util.concurrent.CountDownLatch;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		int[] table = new int[45];
		for (int i = 1; i <= 44; i++) {
			table[i] = table[i - 1] + i;
		}

		int[] flagTable = new int[1001];
		for (int i = 1; i < table.length; i++) {
			for (int j = 1; j < table.length; j++) {
				for (int k = 1; k < table.length; k++) {
					int sum = table[i] + table[j] + table[k];
					if (sum <= 1000)
						flagTable[sum] = 1;
				}
			}
		}
		for (int j = 0; j < t; j++) {
			int n = Integer.parseInt(br.readLine());
			System.out.printf("%d\n", flagTable[n]);
		}
	}
}
