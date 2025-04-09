import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] juwels = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			juwels[i][0] = Integer.parseInt(st.nextToken());
			juwels[i][1] = Integer.parseInt(st.nextToken());
		}

		int[] bags = new int[K];
		for (int i = 0; i < K; i++) {
			bags[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(juwels, Comparator.comparingInt(a -> a[0]));
		Arrays.sort(bags); // 가방 용량 오름차순 정렬

		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		long answer = 0;
		int jewelIndex = 0;

		for (int i = 0; i < K; i++) {
			int capacity = bags[i];

			while (jewelIndex < N && juwels[jewelIndex][0] <= capacity) {
				pq.add(juwels[jewelIndex][1]);
				jewelIndex++;
			}

			if (!pq.isEmpty()) {
				answer += pq.poll();
			}
		}

		System.out.println(answer);
		br.close();
	}
}
