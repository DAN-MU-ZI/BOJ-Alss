import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[] visitied;
	static int[] arr;
	static int[] stk;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		stk = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		visitied = new boolean[N];
		func(0);
		System.out.println(sb);
	}

	private static void func(int cnt) {
		if (M == cnt) {
			for (int i = 0; i < M; i++) {
				sb
					.append(stk[i])
					.append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visitied[i]) {
				stk[cnt] = arr[i];
				func(cnt + 1);
			}
		}
	}
}