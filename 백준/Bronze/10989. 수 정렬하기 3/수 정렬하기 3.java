import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[10001];

		for (int i = 0; i < N; i++) {
			arr[Integer.parseInt(br.readLine())]++;
		}

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < 10001; i++) {
			if (arr[i] != 0) {
				answer.append(String.format(i + "\n").repeat(arr[i]));
			}
		}
		System.out.printf(answer.toString());
	}
}
