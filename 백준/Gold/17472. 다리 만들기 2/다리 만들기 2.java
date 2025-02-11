import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] grid;
	static List<int[]> edges;
	static int[] parent, rank;
	static int islandCount = 0;
	static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		grid = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = solve();
		bw.write(String.valueOf(answer));
		bw.close();
		br.close();
	}

	static int solve() {
		labelIslands();
		edges = findBridges();

		if (edges.isEmpty())
			return -1;

		return kruskal();
	}

	static void labelIslands() {
		boolean[][] visited = new boolean[N][M];
		int islandID = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] == 1 && !visited[i][j]) {
					bfs(i, j, ++islandID, visited);
				}
			}
		}
		islandCount = islandID;
	}

	static void bfs(int x, int y, int islandID, boolean[][] visited) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {x, y});
		visited[x][y] = true;
		grid[x][y] = islandID;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cx = cur[0], cy = cur[1];

			for (int[] d : deltas) {
				int nx = cx + d[0], ny = cy + d[1];

				if (nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && grid[nx][ny] == 1) {
					visited[nx][ny] = true;
					grid[nx][ny] = islandID;
					queue.add(new int[] {nx, ny});
				}
			}
		}
	}

	static List<int[]> findBridges() {
		List<int[]> bridgeList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (grid[i][j] > 0) {
					for (int[] d : deltas) {
						int length = 0, nx = i, ny = j;
						int islandFrom = grid[i][j];

						while (true) {
							nx += d[0];
							ny += d[1];

							if (!isValid(nx, ny) || grid[nx][ny] == islandFrom)
								break;
							if (grid[nx][ny] > 0) {
								if (length >= 2) {
									bridgeList.add(new int[] {length, islandFrom, grid[nx][ny]});
								}
								break;
							}
							length++;
						}
					}
				}
			}
		}

		return bridgeList;
	}

	static int kruskal() {
		edges.sort((e1, e2) -> e1[0] - e2[0]);

		parent = new int[islandCount + 1];
		rank = new int[islandCount + 1];

		for (int i = 1; i <= islandCount; i++) {
			parent[i] = i;
		}

		int mstCost = 0;
		int edgesUsed = 0;

		for (int[] edge : edges) {
			int cost = edge[0], a = edge[1], b = edge[2];

			if (union(a, b)) {
				mstCost += cost;
				edgesUsed++;
				if (edgesUsed == islandCount - 1)
					return mstCost;
			}
		}

		return -1;
	}

	static int find(int x) {
		if (parent[x] != x)
			parent[x] = find(parent[x]);
		return parent[x];
	}

	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB)
			return false;

		if (rank[rootA] > rank[rootB]) {
			parent[rootB] = rootA;
		} else if (rank[rootA] < rank[rootB]) {
			parent[rootA] = rootB;
		} else {
			parent[rootB] = rootA;
			rank[rootA]++;
		}

		return true;
	}

	static boolean isValid(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}
}
