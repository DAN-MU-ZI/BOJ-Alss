import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String[] arr = new String[n];

		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine();
		}

		Arrays.sort(arr, (o1, o2) -> {
			if (o1.length() == o2.length()) {
				int s1 = 0;
				int s2 = 0;

				for (int i = 0; i < o1.length(); i++) {
					if ('0' <= o1.charAt(i) && o1.charAt(i) <= '9') {
						s1 += o1.charAt(i) - '0';
					}
					if ('0' <= o2.charAt(i) && o2.charAt(i) <= '9') {
						s2 += o2.charAt(i) - '0';
					}
				}
				if (s1 == s2) {
					return o1.compareTo(o2);
				}
				return s1 - s2;

			}
			return o1.length() - o2.length();
		});

		for (String s : arr) {
			System.out.println(s);
		}
	}
}