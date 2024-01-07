import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			func(br);
		}
	}

	private static void func(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		int x = n % h;
		int y = n / h;
		if (x == 0) {
			x = h;
		} else{
			y++;
		}

		System.out.printf("%d%02d\n", x, y);
	}

}
