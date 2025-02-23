import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] arr, tree;

	static {
		arr = new int[100001];
		tree = new int[400001];
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while (true) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;

			for (int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			sb.append(solve() + "\n");
		}

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static long solve() {
		build(1, 1, n);

		return getMax();
	}

	static long getMax() {
		return getMax(1, n);
	}

	static long getMax(int left, int right) {
		int m = query(1, 1, n, left, right);

		long area = (long)(right - left + 1) * (arr[m]);
		if (left <= m - 1) {
			area = Math.max(area, getMax(left, m - 1));
		}
		if (m + 1 <= right) {
			area = Math.max(area, getMax(m + 1, right));
		}
		return area;
	}

	private static int query(int node, int start, int end, int left, int right) {
		if (left > end || right < start) {
			return -1;
		}
		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		int leftChild = 2 * node;
		int rightChild = 2 * node + 1;

		int leftNode = query(leftChild, start, mid, left, right);
		int rightNode = query(rightChild, mid + 1, end, left, right);

		if (leftNode == -1) {
			return rightNode;
		}
		if (rightNode == -1) {
			return leftNode;
		}

		return (arr[leftNode] <= arr[rightNode]) ? leftNode : rightNode;
	}

	private static void build(int node, int start, int end) {
		if (start == end) {
			tree[node] = start;
			return;
		}

		int mid = (start + end) / 2;
		int leftChild = 2 * node;
		int rightChild = 2 * node + 1;

		build(leftChild, start, mid);
		build(rightChild, mid + 1, end);

		tree[node] = (arr[tree[leftChild]] <= arr[tree[rightChild]]) ? tree[leftChild] : tree[rightChild];
	}
}