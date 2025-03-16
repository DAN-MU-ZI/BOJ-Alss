import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static long[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = stoi(st);
		M = stoi(st);

		tree = new long[4 * N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int _case = stoi(st);
			int a = stoi(st);
			int b = stoi(st);

			if (_case == 0) {
				long query = query(a, b);
				sb.append(query + "\n");
			} else if (_case == 1) {
				update(a, b);
			}
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static long query(int a, int b) {
		if (a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		return query(1, 1, N, a, b);
	}

	static long query(int node, int start, int end, int L, int R) {
		if (R < start || end < L) {
			return 0;
		}

		if (L <= start && end <= R) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		int leftNode = 2 * node;
		int rightNode = 2 * node + 1;

		return query(leftNode, start, mid, L, R) + query(rightNode, mid + 1, end, L, R);
	}

	static void update(int a, int b) {
		update(1, 1, N, a, b);
	}

	static void update(int node, int start, int end, int target, int val) {
		if (start == end) {
			tree[node] = val;
			return;
		}

		int mid = (start + end) / 2;
		int leftNode = 2 * node;
		int rightNode = 2 * node + 1;
		if (target <= mid) {
			update(leftNode, start, mid, target, val);
		} else {
			update(rightNode, mid + 1, end, target, val);
		}

		tree[node] = tree[leftNode] + tree[rightNode];
	}

	private static int stoi(StringTokenizer st) {
		return Integer.parseInt(st.nextToken());
	}
}
