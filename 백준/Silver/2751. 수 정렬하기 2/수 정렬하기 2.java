import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int RANGE = 2000001;
		int SHIFT = 1000000;
		int[] arr = new int[RANGE];

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[Integer.parseInt(br.readLine()) + SHIFT]++;
		}

		for (int i = 0; i < RANGE; i++) {
			for (int j = 0; j < arr[i]; j++) {
				bw.write(i - SHIFT + "\n");
			}
		}
		bw.close();
	}
}
