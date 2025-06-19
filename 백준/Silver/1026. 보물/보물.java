import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Queue<Integer> A = new PriorityQueue<>();
		Queue<Integer> B = new PriorityQueue<>((s1, s2) -> -(s1 - s2));

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A.add(Integer.parseInt(st.nextToken()));
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B.add(Integer.parseInt(st.nextToken()));
		}

		int tot = 0;
		while (!A.isEmpty() && !B.isEmpty()) {
			tot += A.poll() * B.poll();
		}
		System.out.println(tot);
	}
}

