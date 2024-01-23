import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N, M;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[] arrA = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}

		int[] arrB = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}

		int[] arr = new int[N + M];
		int a = 0, b = 0;
		for (int i = 0; i < M + N; i++) {
			if (a < N && b < M) {
				if (arrA[a] < arrB[b])
					arr[i] = arrA[a++];
				else
					arr[i] = arrB[b++];
			} else if (a == N && b < M) {
				arr[i] = arrB[b++];
			} else if (a < N && b == M) {
				arr[i] = arrA[a++];
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int num : arr) {
			sb.append(num).append(" ");
		}
		System.out.println(sb);
	}
}