import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int p = Integer.parseInt(br.readLine());
		for (int i = 0; i < p; i++) {
			func(br);
		}
	}

	static void func(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int caseNum = Integer.parseInt(st.nextToken());
		int[] arr = new int[20];
		int answer = 0;
		for (int i = 0; i < 20; i++) {
			int n = Integer.parseInt(st.nextToken());
			arr[i] = n;
			for (int j = i; j > 0; j--) {
				for (int k = j; k > 0; k--) {
					if (arr[k] < arr[k - 1]) {
						int tmp = arr[k];
						arr[k] = arr[k - 1];
						arr[k - 1] = tmp;
						answer++;
					}
				}
			}
		}
		System.out.printf("%d %d\n", caseNum, answer);
	}
}
