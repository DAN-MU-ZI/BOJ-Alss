import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		String A = st.nextToken();
		String B = st.nextToken();

		int answer = A.length();
		for (int i = 0; i <= B.length() - A.length(); i++) {
			int cnt = A.length();
			for (int j = 0; j < A.length(); j++) {
				if (A.charAt(j) == B.charAt(i + j)) {
					cnt--;
				}
			}
			answer = Math.min(answer, cnt);
		}
		System.out.println(answer);
	}
}
