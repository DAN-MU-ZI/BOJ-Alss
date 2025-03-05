import java.io.*;
import java.util.*;

public class Solution {
	
	static int H, W, N;
	static char[] commands;
	static char[][] grid;
	static int r, c, d;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			grid = new char[H][W];
			for (int i = 0; i < H; i++) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < W; j++) {
					grid[i][j] = line[j];
					
					if (grid[i][j] == '^') {
						r = i;
						c = j;
						d = 0;
					}else if(grid[i][j] == 'v') {
						r = i;
						c = j;
						d = 1;
					}else if(grid[i][j] == '<') {
						r = i;
						c = j;
						d = 2;
					} else if(grid[i][j] == '>') {
						r = i;
						c = j;
						d = 3;
					}
				}
			}
			
			N = Integer.parseInt(br.readLine());
			commands = new char[N];
			char[] input = br.readLine().toCharArray();
			for (int i = 0; i < N; i++) {
				commands[i] = input[i];
			}
			
			solve();
			
			sb.append(String.format("#%d ", t));
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(grid[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.print(sb.toString());
	}
	
	static void solve() {
		int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		char[] directions = {'^', 'v', '<', '>'};
		for (char command: commands) {
			if (command == 'U') {
				d = 0;
				
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				
				if (isValid(nr, nc) && grid[nr][nc] == '.') {
					grid[r][c] = '.';
					r = nr;
					c = nc;
				}
				grid[r][c] = directions[d];
			} else if (command == 'D') {
				d = 1;
				
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				
				if (isValid(nr, nc) && grid[nr][nc] == '.') {
					grid[r][c] = '.';
					r = nr;
					c = nc;
				}
				grid[r][c] = directions[d];
			} else if (command == 'L') {
				d = 2;
				
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				
				if (isValid(nr, nc) && grid[nr][nc] == '.') {
					grid[r][c] = '.';
					r = nr;
					c = nc;
				}
				grid[r][c] = directions[d];
			} else if (command == 'R') {
				d = 3;
				
				int nr = r + deltas[d][0];
				int nc = c + deltas[d][1];
				
				if (isValid(nr, nc) && grid[nr][nc] == '.') {
					grid[r][c] = '.';
					r = nr;
					c = nc;
				}
				grid[r][c] = directions[d];
			} else if (command == 'S') {
				int sr = r;
				int sc = c;
				
				while(true) {
					int nr = sr + deltas[d][0];
					int nc = sc + deltas[d][1];
					
					if (!isValid(nr, nc)) {
						break;
					} else if (grid[nr][nc] == '*') {
						grid[nr][nc] = '.';
						break;
					} else if (grid[nr][nc] == '#') {
						break;
					}
					
					sr = nr;
					sc = nc;
				}
			}
		}
	}
	
	static boolean isValid(int r, int c) {
		return 0 <= r && r < H && 0 <= c && c < W;
	}
}


