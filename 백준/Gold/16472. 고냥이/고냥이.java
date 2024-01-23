import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		char[] arr = br.readLine().toCharArray();

		int[] counter = new int[26];

		int count = 0, answer = 0;
		int s = 0, e = 0;
		while (e < arr.length) {
			if (counter[arr[e] - 'a'] == 0) {
				count++;
			}
			counter[arr[e] - 'a']++;

			while (N < count && s < e) {
				counter[arr[s] - 'a']--;
				if (counter[arr[s] - 'a'] == 0)
					count--;
				s++;
			}
			answer = Math.max(answer, e - s + 1);
			e++;
		}
		System.out.println(answer);
	}
}