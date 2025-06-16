import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] arr;
	static int[][] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		tree = new int[4 * N][2];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

		build(1, 0, N - 1);

		M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int q = 0; q < M; q++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			if (type == 1) {
				int idx = Integer.parseInt(st.nextToken()) - 1;
				int val = Integer.parseInt(st.nextToken());
				update(1, 0, N - 1, idx, val);
			} else if (type == 2) {
				int l = Integer.parseInt(st.nextToken()) - 1;
				int r = Integer.parseInt(st.nextToken()) - 1;
				int[] res = query(1, 0, N - 1, l, r);
				sb.append(res[0] + res[1]).append('\n');
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}

	static void build(int node, int s, int e) {
		if (s == e) {
			tree[node][0] = arr[s];
			tree[node][1] = Integer.MIN_VALUE;
			return;
		}
		int mid = (s + e) / 2;
		build(node * 2, s, mid);
		build(node * 2 + 1, mid + 1, e);
		tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
	}

	static void update(int node, int s, int e, int idx, int val) {
		if (s == e) {
			tree[node][0] = val;
			tree[node][1] = Integer.MIN_VALUE;
			return;
		}
		int mid = (s + e) / 2;
		if (idx <= mid) update(node * 2, s, mid, idx, val);
		else update(node * 2 + 1, mid + 1, e, idx, val);
		tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
	}

	static int[] query(int node, int s, int e, int l, int r) {
		if (r < s || e < l) return new int[]{Integer.MIN_VALUE, Integer.MIN_VALUE};
		if (l <= s && e <= r) return tree[node];
		int mid = (s + e) / 2;
		int[] left = query(node * 2, s, mid, l, r);
		int[] right = query(node * 2 + 1, mid + 1, e, l, r);
		return merge(left, right);
	}

	static int[] merge(int[] a, int[] b) {
		int[] temp = new int[]{a[0], a[1], b[0], b[1]};
		Arrays.sort(temp);
		return new int[]{temp[3], temp[2]};
	}
}
