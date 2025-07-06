import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Map<Integer, Queue<Integer>> map = new HashMap<>();
		Queue<Integer> q = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int a = Integer.parseInt(st.nextToken());
			Queue<Integer> list = map.getOrDefault(a, new LinkedList<>());
			list.add(i);
			map.put(a, list);
			q.add(a);
		}

		Set<Integer> plugset = new HashSet<>();
		int result = 0;

		for (int i = 0; i < K; i++) {
			int cur = q.poll();
			map.get(cur).poll();

			if (plugset.contains(cur)) continue;

			if (plugset.size() < N) {
				plugset.add(cur);
				continue;
			}

			int farthest = -1, out = -1;
			for(int plugged: plugset) {
				Queue<Integer> next = map.get(plugged);
				if (next.isEmpty()) {
					out = plugged;
					break;
				}
				int nextUse = next.peek();
				if (nextUse > farthest) {
					farthest = nextUse;
					out = plugged;
				}
			}

			plugset.remove(out);
			plugset.add(cur);
			result++;
		}
		System.out.println(result);
	}
}
