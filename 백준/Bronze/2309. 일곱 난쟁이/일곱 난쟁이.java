import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] arr = new int[9];

	public static void main(String[] args) throws IOException {
		int total = 0;
		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			total = total + arr[i];
		}
		Arrays.sort(arr);

		for (int i = 0; i < 8; i++) {
			total = total - arr[i];
			for (int j = i + 1; j < 9; j++) {
				total = total - arr[j];
				if (total == 100) {
					for (int k = 0; k < 9; k++) {
						if (k != i && k != j) {
							System.out.printf("%d\n", arr[k]);
						}
					}
					return;
				}
				total = total + arr[j];
			}
			total = total + arr[i];
		}

	}
}
