import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] charArray = br
			.readLine()
			.toCharArray();

		int answer = 0;
		int height = 0;
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (c == '(') {
				height++;
			} else {
				height--;
				if (charArray[i - 1] == '(')
					answer += height;
				else
					answer++;
			}
		}
		System.out.println(answer);
	}
}