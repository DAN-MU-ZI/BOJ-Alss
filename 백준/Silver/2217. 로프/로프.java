import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(A);

		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, (N - i) * A[i]);
		}

		System.out.println(max);
	}
}

