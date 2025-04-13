import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] weights = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(weights);

		// 현재까지의 합보다 큰 값은 만들 수 없음
		// 그래서 공집합(0)에서 시작하기 때문에 + 1 로 초기화됨
		// target 은 누적 가능한 무게의 최댓값 + 1 을 의미하게 된다.
		int target = 1;
		for (int i = 0; i < N; i++) {
			if (weights[i] > target)
				break;
			target += weights[i]; // 상한선 조정
		}

		System.out.println(target);
	}
}
