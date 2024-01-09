import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n, m;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		boolean[] a = new boolean[100000001];
		boolean[] b = new boolean[100000001];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[Integer.parseInt(st.nextToken())] = true;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			b[Integer.parseInt(st.nextToken())] = true;
		}

		int answer = 0;
		for (int i = 1; i < 100000001; i++) {
			if (a[i])
				answer++;
			if (b[i])
				answer++;
			if (a[i] && b[i]) {
				answer -= 2;
			}
		}
		System.out.println(answer);
	}
}
