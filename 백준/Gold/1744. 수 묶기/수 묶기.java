import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Queue<Integer> leq = new PriorityQueue<>();
		Queue<Integer> lt = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (num <= 0) {
				leq.add(num);
			} else {
				lt.add(-num);
			}
		}

		int tot = 0;
		while (leq.size() >= 2) {
			tot += leq.poll() * leq.poll();
		}
		if (!leq.isEmpty()) {
			tot += leq.poll();
		}
		while (lt.size() >= 2) {
			int a = -lt.poll();
			int b = -lt.poll();
			if (a + b >= a * b) {
				tot += a + b;
			} else {
				tot += a * b;
			}

		}
		if (!lt.isEmpty()) {
			tot += (-lt.poll());
		}
		System.out.println(tot);
	}
}
