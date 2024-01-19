import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] gate = new int[N];
		for (int i = 0; i < N; i++) {
			gate[i] = Integer.parseInt(br.readLine());
		}
		long s = 1;
		long e = (long)Arrays.stream(gate).min().getAsInt() * M;
		long minTime = 0;
		while (s <= e) {
			long mid = (s + e) / 2;
			if (getPop(gate, mid) >= M) {
				minTime = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}
		}
		System.out.println(minTime);
	}

	private static long getPop(int[] gate, long mid) {
		long result = 0;
		for (long g : gate) {
			result += mid / g;
		}
		return result;
	}
}