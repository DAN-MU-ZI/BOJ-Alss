import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] charArray = br
			.readLine()
			.toCharArray();
		char[] tmp = new char[charArray.length];

		int idx = 0;
		for (char c : charArray) {
			tmp[idx++] = c;
			if (idx >= 4 &&
				tmp[idx - 1] == 'P' &&
				tmp[idx - 2] == 'A' &&
				tmp[idx - 3] == 'P' &&
				tmp[idx - 4] == 'P') {
				idx -= 3;
			}
		}

		System.out.println(idx == 1 && tmp[0] == 'P' ? "PPAP" : "NP");
	}
}