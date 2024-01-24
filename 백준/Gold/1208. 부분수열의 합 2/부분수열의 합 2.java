import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static ArrayList<Long> left = new ArrayList<>();
	static ArrayList<Long> right = new ArrayList<>();
	static int N;
	static int S;
	private static long answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		func(0, N / 2, 0, left);
		func(N / 2, N, 0, right);
		Collections.sort(left);
		Collections.sort(right);

		int s = 0, e = right.size() - 1;
		while (s < left.size() && e >= 0) {
			long sum = left.get(s) + right.get(e);
			if (sum == S) {
				long subLeft = left.get(s);
				long leftCnt = 0;
				while (s < left.size() && left.get(s) == subLeft) {
					leftCnt++;
					s++;
				}

				long subRight = right.get(e);
				long rightCnt = 0;
				while (e >= 0 && right.get(e) == subRight) {
					rightCnt++;
					e--;
				}

				answer += leftCnt * rightCnt;
			} else if (sum < S) {
				s++;
			} else {
				e--;
			}
		}
		System.out.println(S == 0 ? answer - 1 : answer);
	}

	private static void func(int idx, int end, long sum, ArrayList<Long> subArr) {
		if (idx == end) {
			subArr.add(sum);
			return;
		}

		func(idx + 1, end, sum, subArr);
		func(idx + 1, end, sum + arr[idx], subArr);
	}
}
