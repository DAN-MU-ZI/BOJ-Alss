import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int answer = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		char[][] arr = new char[n][n];
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (j + 1 < n && arr[i][j] != arr[i][j + 1]) {
					swap(arr, i, j, i, j + 1);
					getMaxCount(arr);
					swap(arr, i, j + 1, i, j);
				}

				if (i + 1 < n && arr[i][j] != arr[i + 1][j]) {
					swap(arr, i, j, i + 1, j);
					getMaxCount(arr);
					swap(arr, i + 1, j, i, j);
				}
			}
		}
		System.out.println(answer);
	}

	static void swap(char[][] arr, int x1, int y1, int x2, int y2) {
		char tmp;
		tmp = arr[x1][y1];
		arr[x1][y1] = arr[x2][y2];
		arr[x2][y2] = tmp;
	}

	static void getMaxCount(char[][] arr) {
		for (int j = 0; j < arr.length; j++) {
			int cnt = 1;
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i][j] == arr[i + 1][j]) {
					cnt++;
					answer = Math.max(answer, cnt);
				} else {
					cnt = 1;
				}
			}
		}

		for (char[] chars : arr) {
			int cnt = 1;
			for (int j = 0; j < arr.length - 1; j++) {
				if (chars[j] == chars[j + 1]) {
					cnt++;
					answer = Math.max(answer, cnt);
				} else {
					cnt = 1;
				}
			}
		}
	}
}
