import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arrA = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			arrA[i] = Integer.parseInt(st.nextToken()) + arrA[i - 1];
		}

		Map<Integer, Integer> mapA = new HashMap<>();
		for (int i = 1; i < n + 1; i++) {
			for (int j = 0; j < i; j++) {
				int sum = arrA[i] - arrA[j];
				if (mapA.containsKey(sum))
					mapA.replace(sum, mapA.get(sum) + 1);
				else
					mapA.put(sum, 1);
			}
		}

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arrB = new int[m + 1];
		for (int i = 1; i < m + 1; i++) {
			arrB[i] = Integer.parseInt(st.nextToken()) + arrB[i - 1];
		}

		Map<Integer, Integer> mapB = new HashMap<>();
		for (int i = 1; i < m + 1; i++) {
			for (int j = 0; j < i; j++) {
				int sum = arrB[i] - arrB[j];
				if (mapB.containsKey(sum))
					mapB.replace(sum, mapB.get(sum) + 1);
				else
					mapB.put(sum, 1);
			}
		}

		long answer = 0;
		for (int a : mapA.keySet()) {
			int target = t - a;
			Integer rangeB = mapB.get(target);
			if (rangeB == null)
				continue;
			Integer rangeA = mapA.get(a);
			answer += (long)rangeA * rangeB;
		}
		System.out.println(answer);
	}
}