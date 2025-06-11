import java.util.*;
import java.io.*;

public class Main {

	static int N, M;
	static long[] seg;
	static int size;

	static void update(int node, int start, int end, int idx, long diff) {
		if (idx < start || idx > end)
			return;
		seg[node] += diff;
		if (start == end)
			return;
		int mid = (start + end) / 2;
		update(node * 2, start, mid, idx, diff);
		update(node * 2 + 1, mid + 1, end, idx, diff);
	}

	static long query(int node, int start, int end, int l, int r) {
		if (r < start || end < l)
			return 0;
		if (l <= start && end <= r)
			return seg[node];
		int mid = (start + end) / 2;
		return query(node * 2, start, mid, l, r) +
			query(node * 2 + 1, mid + 1, end, l, r);
	}

	static class SumQ {
		int k, l, r, idx;
	}

	static class UpdQ {
		int idx, val;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		seg = new long[N * 4 + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			long v = Long.parseLong(st.nextToken());
			update(1, 1, N, i, v);
		}

		M = Integer.parseInt(br.readLine());
		List<UpdQ> updList = new ArrayList<>();
		List<SumQ> sumList = new ArrayList<>();
		int sumCount = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			if (t == 1) {
				int k = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());

				UpdQ uq = new UpdQ();
				uq.idx = k;
				uq.val = j;

				updList.add(uq);
			} else {
				int k = Integer.parseInt(st.nextToken());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());

				SumQ sq = new SumQ();
				sq.k = k;
				sq.l = l;
				sq.r = r;
				sq.idx = sumCount++;

				sumList.add(sq);
			}
		}

		sumList.sort(Comparator.comparingInt(a -> a.k));

		long[] ans = new long[sumCount];
		int applied = 0;
		for (SumQ sq : sumList) {
			while (applied < sq.k) {
				UpdQ u = updList.get(applied);
				long current = query(1, 1, N, u.idx, u.idx);
				update(1, 1, N, u.idx, u.val - current);
				applied++;
			}
			ans[sq.idx] = query(1, 1, N, sq.l, sq.r);
		}

		for (long x : ans)
			bw.write(x + "\n");
		bw.flush();
	}
}
