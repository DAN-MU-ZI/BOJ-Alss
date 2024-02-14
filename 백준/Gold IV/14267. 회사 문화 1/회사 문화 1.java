import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int[] happy;
	static int[] like;
	static ArrayList<Integer>[] child;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] parent = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n + 1; i++) {
			parent[i] = Integer.parseInt(st.nextToken());
		}

		child = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			child[i] = new ArrayList<>();
		}
		for (int i = 2; i < n + 1; i++) {
			child[parent[i]].add(i);
		}

		happy = new int[n + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			happy[idx] += w;
		}

		LinkedList<Integer> stk = new LinkedList<>();
		stk.push(1);
		while (!stk.isEmpty()) {
			Integer cur = stk.pop();
			for (Integer node : child[cur]) {
				happy[node] += happy[cur];
				stk.offerLast(node);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < n + 1; i++) {
			sb
				.append(happy[i])
				.append(" ");
		}
		System.out.println(sb);
	}
}