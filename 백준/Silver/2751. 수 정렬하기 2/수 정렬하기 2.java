import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int RANGE = 2000001;
		int SHIFT = 1000000;
		boolean[] arr = new boolean[RANGE];

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[Integer.parseInt(br.readLine()) + SHIFT] = true;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < RANGE; i++) {
			if (arr[i]) {
				sb.append(i - SHIFT).append("\n");
			}
		}
		System.out.println(sb);
	}
}
