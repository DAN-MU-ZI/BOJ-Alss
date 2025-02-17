import java.io.*;
import java.util.*;

public class Main {
	static int R, C, N;
	static char[][] grid;
	static int[][] counter;
	
	
	public static void main(String[] args) throws Exception {
		//--------------솔루션 코드를 작성하세요.--------------------------------
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		grid = new char[R][C];
		for (int r = 0; r < R; r++) {
			char[] line = br.readLine().trim().toCharArray();
			for (int c = 0; c < C; c++) {
				grid[r][c] = line[c];
			}
		}
		
		
		if (N >= 2) {
			N %= 4;
			N += 4;
		}
		solve();

		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sb.append(counter[r][c] > 0 ? 'O':'.');
			}
			sb.append("\n");
		}
		

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static void solve() {
		
		init();
		discount();
		while (N > 0) {
			installMines();
			
			boom();
			
			if (N > 0) {
				discount();
				boom();	
			}
		}
	}
	
	static void print() { 
		for (int[] line: counter) {
			System.out.println(Arrays.toString(line));
		}
		System.out.println();
	}
	
	static void installMines() {
		N--;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (counter[r][c] == 0) counter[r][c] = 3;
				else counter[r][c]--;
			}
		}
	}
	
	static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static void boom() {
		int[][] copy = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				copy[r][c] = counter[r][c];
			}
		}
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (counter[r][c] == 0) {
					for (int[] delta: deltas) {
						int nr = r + delta[0];
						int nc = c + delta[1];
						
						if (isValid(nr, nc)) {
							copy[nr][nc] = 0;
						}
					}
					copy[r][c] = 0;
				}
			}
		}
		counter = copy;
	}
	
	static boolean isValid(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
	static void discount() {
		N--;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (counter[r][c] > 0) counter[r][c]--;
			}
		}
	}
	
	static void init() {
		counter = new int[R][C];
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (grid[r][c] == 'O') counter[r][c] = 3;
			}
		}
	}
}
