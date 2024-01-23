import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] arr = new int[9];

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		String n = st.nextToken();
		int b = Integer.parseInt(st.nextToken());

		int[] dict = new int[128];
		for (char c = '0'; c <= '9'; c++) {
			dict[c] = c - '0';
		}
		for (char c = 'A'; c <= 'Z'; c++) {
			dict[c] = c - 'A' + 10;
		}

		int answer = 0;
		for (char c : n.toCharArray()) {
			answer *= b;
			answer += dict[c];
		}
		System.out.printf("%d\n", answer);
	}
}
