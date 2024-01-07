import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static char[][] board;

	static boolean checkOutOfBound(int x, int y, int n) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}

	static void printBoard() {
		for (char[] chars : board) {
			for (int j = 0; j < board.length; j++) {
				System.out.printf("%c", chars[j]);
			}
			System.out.println();
		}
	}

	static void moveHorizontal(int x, int y) {
		if (board[x][y] == '.') {
			board[x][y] = '-';
		} else if (board[x][y] == '|') {
			board[x][y] = '+';
		}
	}

	static void moveVertical(int x, int y) {
		if (board[x][y] == '.') {
			board[x][y] = '|';
		} else if (board[x][y] == '-') {
			board[x][y] = '+';
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		board = new char[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = '.';
			}
		}

		String commands = br.readLine();

		int x = 0;
		int y = 0;
		for (int i = 0; i < commands.length(); i++) {
			int nx = x;
			int ny = y;
			if (commands.charAt(i) == 'D') {
				nx++;
				if (checkOutOfBound(nx, ny, n)) {
					moveVertical(x, y);
					moveVertical(nx, ny);
					x = nx;
				}
			} else if (commands.charAt(i) == 'U') {
				nx--;
				if (checkOutOfBound(nx, ny, n)) {
					moveVertical(x, y);
					moveVertical(nx, ny);
					x = nx;
				}
			} else if (commands.charAt(i) == 'L') {
				ny--;
				if (checkOutOfBound(nx, ny, n)) {
					moveHorizontal(x, y);
					moveHorizontal(nx, ny);
					y = ny;
				}
			} else if (commands.charAt(i) == 'R') {
				ny++;
				if (checkOutOfBound(nx, ny, n)) {
					moveHorizontal(x, y);
					moveHorizontal(nx, ny);
					y = ny;
				}
			}
		}
		printBoard();
	}

}
