import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[] arr;
	static int answer;

	public static void main(String[] args) throws IOException {
		int p = Integer.parseInt(br.readLine());
		for (int i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();

			arr = new int[20];
			answer = 0;
			for (int k = 0; k < 20; k++) {
				arr[k] = Integer.parseInt(st.nextToken());
			}
			for (int k = 0; k < 20; k++) {
				for (int j = 0; j < k; j++) {
					if (arr[k] < arr[j]) {
						answer++;
					}
				}
			}
			sb.append(String.format("%d %d\n", i + 1, answer));
		}
		bw.write(String.valueOf(sb));
		bw.close();
	}

}
