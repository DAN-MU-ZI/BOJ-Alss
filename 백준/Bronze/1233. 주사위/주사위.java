import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s1, s2, s3;
		s1 = Integer.parseInt(st.nextToken());
		s2 = Integer.parseInt(st.nextToken());
		s3 = Integer.parseInt(st.nextToken());
		int[] arr = new int[s1 + s2 + s3 + 1];
		for (int i = 1; i <= s1; i++) {
			for (int j = 1; j <= s2; j++) {
				for (int k = 1; k <= s3; k++) {
					arr[i + j + k]++;
				}
			}
		}
		int maxValue = 0;
		int idx = 0;
		for (int i = 1; i < arr.length; i++) {
			if (maxValue < arr[i]) {
				maxValue = arr[i];
				idx = i;
			}
		}
		System.out.println(idx);
	}
}
