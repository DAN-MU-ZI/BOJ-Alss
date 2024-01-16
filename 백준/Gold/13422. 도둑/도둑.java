import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			sb.append(func(br)).append("\n");
		}
		System.out.println(sb);
	}

	static int func(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		boolean[] visited = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int sum = 0;
		for (int i = 0; i < M; i++) {
			sum += arr[i];
		}

		if (N == M) {
			return sum < K ? 1 : 0;
		} else {
			int answer = 0;
			int idx = M;

			while (!visited[idx]) {
				visited[idx] = true;
				sum += arr[idx];
				sum -= arr[(N + idx - M) % N];
				idx = (idx + 1) % N;
				if (sum < K)
					answer++;
			}
			return answer;
		}
	}
}