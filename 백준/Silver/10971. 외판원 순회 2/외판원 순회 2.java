import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		func(0, 0, 0, 0);
		System.out.println(answer);
	}

	static void func(int src, int dst, int sum, int depth) {
		if (depth == N && src == dst) {
			answer = Math.min(answer, sum);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i] && arr[dst][i] != 0) {
				visited[i] = true;
				func(src, i, sum + arr[dst][i], depth + 1);
				visited[i] = false;
			}
		}
	}
}
