import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int K = Integer.parseInt(br.readLine()) + 1;

		StringBuilder sb = new StringBuilder();
		while (K > 1) {
			if (K % 2 == 0)
				sb.append(4);
			else
				sb.append(7);
			K /= 2;
		}
		System.out.println(sb.reverse());
	}
}