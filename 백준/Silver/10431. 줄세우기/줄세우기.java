import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int p = Integer.parseInt(br.readLine());
		for (int i = 0; i < p; i++) {
			func(br);
		}
		System.out.println(sb);
	}

	static void func(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int caseNum = Integer.parseInt(st.nextToken());
		int[] arr = new int[20];
		int answer = 0;
		for (int i = 0; i < 20; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] < arr[j]) {
					answer++;
				}
			}
		}
		sb.append(String.format("%d %d\n", caseNum, answer));
	}
}
