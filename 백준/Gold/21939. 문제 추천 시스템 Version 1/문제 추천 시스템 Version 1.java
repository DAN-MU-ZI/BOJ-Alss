import java.io.*;
import java.util.*;

public class Main {
	static class Problem implements Comparable<Problem> {
		int id, level;

		public Problem(int _id, int _level) {
			id = _id;
			level = _level;
		}

		public int compareTo(Problem p) {
			if (level == p.level)
				return id - p.id;
			return level - p.level;
		}
	}

	static TreeSet<Problem> set;
	static HashMap<Integer, Problem> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		set = new TreeSet<>();
		map = new HashMap<>();

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int id = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			Problem problem = new Problem(id, level);
			set.add(problem);
			map.put(id, problem);
		}

		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken().trim();

			if (command.equals("recommend")) {
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {
					sb.append(set.last().id + "\n");
				} else {
					sb.append(set.first().id + "\n");
				}
			} else if (command.equals("add")) {
				int P = Integer.parseInt(st.nextToken());
				int L = Integer.parseInt(st.nextToken());
				Problem problem = new Problem(P, L);
				set.add(problem);
				map.put(P, problem);
			} else if (command.equals("solved")) {
				int P = Integer.parseInt(st.nextToken());
				set.remove(map.get(P));
			}
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
