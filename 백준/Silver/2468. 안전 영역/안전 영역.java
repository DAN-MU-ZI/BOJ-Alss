import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] grid;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		grid = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(solve());
	}

	static int solve() {
		int answer = 0;
		
		for (int num = 0; num <= 100; num++) {
			int count = simulate(num);

			answer = Math.max(answer, count);
		}
		
		return answer;
	}
	
	static int simulate(int num) {
		int count = 0;
		boolean[][] tmp = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!tmp[i][j] && grid[i][j] > num) {
					count++;
					bfs(tmp, i, j, num);
				}
			}
		}
		
		return count;
	}
	
	static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	static void bfs(boolean[][] visited, int i, int j, int num) {
		Deque<int[]> dq = new ArrayDeque<>();
		
		dq.add(new int[] {i, j});
		visited[i][j] = true;
		
		while (!dq.isEmpty()) {
			int[] cur = dq.poll();
			int r = cur[0];
			int c = cur[1];
			
			for (int[] delta: deltas) {
				int nr = r + delta[0];
				int nc = c + delta[1];
				
				if (isValid(nr,nc) && !visited[nr][nc] && grid[nr][nc] > num) {
					visited[nr][nc] = true;
					dq.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	static boolean isValid(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
