import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static char[][] grid = new char[50][50];
	static Node[] nodes = new Node[251];
	static Node[][] nGrid = new Node[50][50];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int kIdx = 0;
		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				char value = line[j];
				grid[i][j] = value;

				if (value == 'S' || value == 'K') {
					Node node = new Node(i, j, kIdx);
					nodes[kIdx++] = node;
					nGrid[i][j] = node;
				}
			}
		}

		int answer = solve();

		System.out.println(answer);
	}

	static class Edge implements Comparable<Edge> {
		int src, dst, weight;

		Edge(int start, int end, int weight) {
			this.src = start;
			this.dst = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static class Node {
		int r, c, id;

		Node(int r, int c, int id) {
			this.r = r;
			this.c = c;
			this.id = id;
		}
	}

	static PriorityQueue<Edge> pq;

	static int solve() {
		pq = new PriorityQueue<>();
		for (int i = 0; i <= M; i++) {
			bfs(i);
		}

		return kruskal();
	}

	static class UnionFind {
		int[] parent;

		UnionFind(int n) {
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}

		public int find(int x) {
			if (parent[x] != x) {
				parent[x] = find(parent[x]);
			}
			return parent[x];
		}

		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);

			if (rootX < rootY) {
				parent[rootY] = rootX;
			} else if (rootX > rootY) {
				parent[rootX] = rootY;
			}
		}
	}

	static int kruskal() {
		UnionFind uf = new UnionFind(M + 1);

		int cost = 0;
		int eCount = 0;
		while (!pq.isEmpty() && eCount < M) {
			Edge e = pq.poll();

			int rootS = uf.find(e.src);
			int rootD = uf.find(e.dst);

			if (rootS != rootD) {
				uf.union(rootS, rootD);
				eCount++;
				cost += e.weight;
			}
		}

		return eCount == M ? cost : -1;
	}

	static int[] dr = {1, 0, -1, 0};
	static int[] dc = {0, 1, 0, -1};

	static void bfs(int start) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		Node startNode = nodes[start];
		q.add(new int[] {startNode.r, startNode.c, 0});
		visited[startNode.r][startNode.c] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				int nCost = cur[2] + 1;

				if (!isValid(nr, nc))
					continue;
				if (visited[nr][nc])
					continue;
				if (grid[nr][nc] == '1')
					continue;
				visited[nr][nc] = true;

				if (grid[nr][nc] == 'S' || grid[nr][nc] == 'K') {
					Node node = nGrid[nr][nc];
					pq.add(new Edge(startNode.id, node.id, nCost));
				}
				q.add(new int[] {nr, nc, nCost});
			}
		}
	}

	static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}