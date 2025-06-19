import java.io.*;
import java.util.*;

public class Main {
	static class Problem {
		int deadline, cupRamen;

		public Problem(int deadline, int cupRamen) {
			this.deadline = deadline;
			this.cupRamen = cupRamen;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		List<Problem> problems = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			problems.add(new Problem(d, c));
		}

		problems.sort(Comparator.comparingInt(p -> p.deadline));

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for (Problem p : problems) {
			pq.add(p.cupRamen);
			if (pq.size() > p.deadline) {
				pq.poll();
			}
		}

		long total = 0;
		while (!pq.isEmpty()) {
			total += pq.poll();
		}

		System.out.println(total);
	}
}
