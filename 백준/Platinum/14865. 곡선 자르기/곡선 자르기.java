import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] lines;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		lines = new int[N][2];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[] answer = solve();
		bw.write(String.format("%d %d\n", answer[0], answer[1]));
		bw.close();
		br.close();
	}

	static List<int[]> list;
	static int a, b;
	static int[] solve() {
		list = new ArrayList<>();
		Stack<Integer> stk = new Stack<>();

		int[] p1 = new int[2];
		p1[0] = lines[0][0];
		p1[1] = lines[0][1];

		for (int i = 1; i < N; i++) {
			int[] p2 = lines[i];

			if (p1[0] == p2[0]) {
				if (p1[1] < 0 && p2[1] > 0) {
					stk.push(p1[0]);
				}
					
				if (p1[1] > 0 && p2[1] < 0) {
					if (stk.isEmpty()) {
						stk.push(p1[0]);
					} else {
						int x = stk.pop();
						int x1 = Math.min(x, p1[0]);
						int x2=  Math.max(x, p1[0]);
						list.add(new int[]{x1, x2});
					}
				}
			}

			p1 = p2;
		}

		if (!stk.isEmpty()) {
			int x = stk.pop();
			int x1 = Math.min(x, lines[0][0]);
			int x2 = Math.max(x, lines[0][0]);
			list.add(new int[]{x1, x2});
		}


		list.sort((s1, s2) -> s1[0] - s2[0]);
		
		a = list.size();
		b = 0;
		for (int i = 0; i < list.size();) {
			i = dfs(i);
		}

		return new int[] {a, b};
	}

	static int dfs(int idx) {
		boolean isOnly = true;

		int j;
		for (j = idx + 1; j < list.size();) {
			if (list.get(j)[0] < list.get(idx)[1]) {
				isOnly = false;
				a--;
				j = dfs(j);
			} else {
				break;
			}
		}
		if (isOnly) b++;
		return j;
	}
}
