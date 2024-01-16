import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		while (N-- > 0) {
			char[] str = br.readLine().toCharArray();

			System.out.println(checkPalindrome(str, 0, str.length - 1, 0));
		}
	}

	private static int checkPalindrome(char[] str, int s, int e, int skip) {
		if (skip > 1)
			return 2;

		int answer = skip;
		while (s <= e) {
			if (str[s] != str[e]) {
				answer = Math.min(checkPalindrome(str, s + 1, e, skip + 1), checkPalindrome(str, s, e - 1, skip + 1));
				break;
			}
			s++;
			e--;
		}
		return answer;
	}
}