import java.io.*;
import java.util.*;

public class Main {
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();

		arr = new int[9];
		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int[] answer = solve();
		for (int ans : answer) {
			sb.append(ans + "\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static boolean[] visited;
	static int[] answer;
	static boolean isFind;

	static int[] solve() {
		visited = new boolean[9];
		isFind = false;
		answer = new int[7];

		dfs(0, 0);

		return answer;
	}

	static void dfs(int idx, int stk) {
		if (isFind)
			return;

		if (idx == 9) {
			if (stk != 7)
				return;

			int sum = 0;
			int j = 0;
			for (int i = 0; i < 9; i++) {
				if (visited[i]) {
					sum += arr[i];
					answer[j++] = arr[i];
				}
			}

			if (sum == 100) {
				isFind = true;
			}

			return;
		}

		visited[idx] = true;
		dfs(idx + 1, stk + 1);
		visited[idx] = false;
		dfs(idx + 1, stk);
	}
}
