import java.io.*;
import java.util.*;

public class Main {

	static boolean[][] isTree;
	static int wRow, wCol;
	static boolean isVertical;
	static int[][] toPos;

	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		isTree = new boolean[N][N];

		int prevFromRow = -1;
		int prevFromCol = -1;
		int fCnt = 0;

		toPos = new int[3][2];
		int tIdx = 0;

		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				if (line[j] == 'B') {
					if (fCnt == 1) {
						isVertical = i - prevFromRow != 0 && j - prevFromCol == 0;
						wRow = i;
						wCol = j;
					}
					prevFromRow = i;
					prevFromCol = j;
					fCnt++;
				} else if (line[j] == '1') {
					isTree[i][j] = true;
				} else if (line[j] == 'E') {
					toPos[tIdx++] = new int[] {i, j};
				}
			}
		}

		int answer = solve();
		System.out.println(answer);

		br.close();
	}

	static int solve() {
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		boolean[][][] visited = new boolean[N][N][2];

		dq.add(new int[] {wRow, wCol, isVertical ? 1 : 0, 0});
		visited[wRow][wCol][1] = isVertical;

		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0];
			int c = cur[1];
			int isVt = cur[2];
			int cnt = cur[3];
			// System.out.printf("%d %d %d %d\n", r, c, isVt, cnt);

			if (isVt == 1) {
				boolean chk = true;
				for (int i = -1; i <= 1; i++) {
					if (!(toPos[i + 1][0] == r + i && toPos[i + 1][1] == c)) {
						chk = false;
						break;
					}
				}
				if (chk) {
					return cnt;
				}

				int nr, nc;
				// 아래로 한칸
				nr = r + 1;
				nc = c;
				if (
					nr + 1 < N
						&& !visited[nr][nc][isVt]
						&& !isTree[nr - 1][nc] && !isTree[nr][nc] & !isTree[nr + 1][nc]
				) {
					visited[nr][nc][isVt] = true;
					dq.add(new int[] {nr, nc, isVt, cnt + 1});
				}

				// 위로 한칸
				nr = r - 1;
				nc = c;
				if (
					0 <= nr - 1
						&& !visited[nr][nc][isVt]
						&& !isTree[nr - 1][nc] && !isTree[nr][nc] & !isTree[nr + 1][nc]
				) {
					visited[nr][nc][isVt] = true;
					dq.add(new int[] {nr, nc, isVt, cnt + 1});
				}

				// 왼쪽 한칸
				nr = r;
				nc = c - 1;
				if (
					0 <= nc
						&& !visited[nr][nc][isVt]
						&& !isTree[nr - 1][nc] && !isTree[nr][nc] && !isTree[nr + 1][nc]

				) {
					visited[nr][nc][isVt] = true;
					dq.add(new int[] {nr, nc, isVt, cnt + 1});
				}

				// 오른쪽 한칸
				nr = r;
				nc = c + 1;
				if (
					nc < N
						&& !visited[nr][nc][isVt]
						&& !isTree[nr - 1][nc] && !isTree[nr][nc] && !isTree[nr + 1][nc]

				) {
					visited[nr][nc][isVt] = true;
					dq.add(new int[] {nr, nc, isVt, cnt + 1});
				}
			} else {
				boolean chk = true;
				for (int i = -1; i <= 1; i++) {
					if (!(toPos[i + 1][0] == r && toPos[i + 1][1] == c + i)) {
						chk = false;
						break;
					}
				}
				if (chk) {
					return cnt;
				}

				int nr, nc;
				// 아래로 한칸
				nr = r + 1;
				nc = c;
				if (
					nr < N
						&& !visited[nr][nc][isVt]
						&& !isTree[nr][nc - 1] && !isTree[nr][nc] & !isTree[nr][nc + 1]
				) {
					visited[nr][nc][isVt] = true;
					dq.add(new int[] {nr, nc, isVt, cnt + 1});
				}

				// 위로 한칸
				nr = r - 1;
				nc = c;
				if (
					0 <= nr
						&& !visited[nr][nc][isVt]
						&& !isTree[nr][nc - 1] && !isTree[nr][nc] & !isTree[nr][nc + 1]
				) {
					visited[nr][nc][isVt] = true;
					dq.add(new int[] {nr, nc, isVt, cnt + 1});
				}

				// 왼쪽 한칸
				nr = r;
				nc = c - 1;
				if (
					0 <= nc - 1
						&& !visited[nr][nc][isVt]
						&& !isTree[nr][nc - 1] && !isTree[nr][nc] & !isTree[nr][nc + 1]

				) {
					visited[nr][nc][isVt] = true;
					dq.add(new int[] {nr, nc, isVt, cnt + 1});
				}

				// 오른쪽 한칸
				nr = r;
				nc = c + 1;
				if (
					nc + 1 < N
						&& !visited[nr][nc][isVt]
						&& !isTree[nr][nc - 1] && !isTree[nr][nc] && !isTree[nr][nc + 1]

				) {
					visited[nr][nc][isVt] = true;
					dq.add(new int[] {nr, nc, isVt, cnt + 1});
				}
			}

			int nr, nc;
			nr = r;
			nc = c;
			int nextDirection = isVt == 1 ? 0 : 1;
			if (
				!visited[nr][nc][nextDirection]
					&& 0 <= nr - 1 && nr + 1 < N && 0 <= nc - 1 && nc + 1 < N
			) {
				boolean canRotate = true;
				Loop:
				for (int i = r - 1; i <= r + 1; i++) {
					for (int j = c - 1; j <= c + 1; j++) {
						if (isTree[i][j]) {
							canRotate = false;
							// System.out.println("cannot rotate");
							// System.out.printf("in here : %d %d %d %d\n", r, c, isVt, cnt);
							// System.out.printf("because : %d %d is tree\n", i, j);
							break Loop;
						}
					}
				}
				if (canRotate) {
					visited[nr][nc][nextDirection] = true;
					dq.add(new int[] {nr, nc, nextDirection, cnt + 1});
				}
			}
		}

		return 0;
	}
}
