import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		StringBuilder[] arr = new StringBuilder[201];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());

			if (arr[age] == null) {
				arr[age] = new StringBuilder();
			}
			arr[age].append(age).append(" ").append(st.nextToken()).append("\n");
		}
		for (StringBuilder sb : arr) {
			if (sb != null) {
				bw.write(String.valueOf(sb));
			}
		}
		bw.close();
	}
}
