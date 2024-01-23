import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] charArr = new int[26];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			int mul = 1;
			for (int j = str.length() - 1; j >= 0; j--) {
				charArr[str.charAt(j) - 'A'] += mul;
				mul *= 10;
			}
		}

		Arrays.sort(charArr);

		int answer = 0;
		for (int i = 0; i < 10; i++) {
			answer += charArr[16 + i] * i;
		}
		System.out.println(answer);
	}
}