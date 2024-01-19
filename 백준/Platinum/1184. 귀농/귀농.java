import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// int[][] acc = new int[N + 1][N + 1];
		// for (int i = 1; i < N + 1; i++) {
		// 	for (int j = 1; j < N + 1; j++) {
		// 		acc[i][j] = acc[i][j - 1] + acc[i - 1][j] - acc[i - 1][j - 1] + arr[i][j];
		// 	}
		// }

		// for (int i = 0; i < N + 1; i++) {
		// 	System.out.println(Arrays.toString(acc[i]));
		// }

		List<Integer> sum1 = new ArrayList<>();
		List<Integer> sum2 = new ArrayList<>();
		List<Integer> sum3 = new ArrayList<>();
		List<Integer> sum4 = new ArrayList<>();

		int[][] tmp = new int[N + 1][N + 1];

		int answer = 0;
		for (int x = 1; x < N; x++) {
			for (int y = 1; y < N; y++) {
				// System.out.println("x, y =  " + x + "," + y);

				// left up dx,dy=(-, -) range 1 ~ X
				for (int x1 = x; x1 >= 1; x1--) {
					for (int y1 = y; y1 >= 1; y1--) {
						tmp[x1][y1] = arr[x1][y1];
						if (x1 < x && y1 < y) {
							tmp[x1][y1] += tmp[x1 + 1][y1] + tmp[x1][y1 + 1] - tmp[x1 + 1][y1 + 1];
						} else if (x1 < x) {
							tmp[x1][y1] += tmp[x1 + 1][y1];
						} else if (y1 < y) {
							tmp[x1][y1] += tmp[x1][y1 + 1];
						}
						sum1.add(tmp[x1][y1]);
					}
				}
				// left down dx,dy=(+, +) range X+1 ~ N
				for (int x1 = x + 1; x1 <= N; x1++) {
					for (int y1 = y + 1; y1 <= N; y1++) {
						tmp[x1][y1] = arr[x1][y1];
						if (x1 > x + 1 && y1 > y + 1) {
							tmp[x1][y1] += tmp[x1 - 1][y1] + tmp[x1][y1 - 1] - tmp[x1 - 1][y1 - 1];
						} else if (x1 > x + 1) {
							tmp[x1][y1] += tmp[x1 - 1][y1];
						} else if (y1 > y + 1) {
							tmp[x1][y1] += tmp[x1][y1 - 1];
						}
						sum2.add(tmp[x1][y1]);
					}
				}

				// System.out.println(sum1);
				// System.out.println(sum2);
				for (int i : sum1) {
					for (int j : sum2) {
						if (i == j)
							answer++;
					}
				}

				// System.out.println("sum1.size() = " + sum1.size());
				// System.out.println("sum2.size() = " + sum2.size());
				// right up dx,dy=(-, +) range 1 ~ x, y+1 ~ N
				for (int x1 = x; x1 >= 1; x1--) {
					for (int y1 = y + 1; y1 <= N; y1++) {
						tmp[x1][y1] = arr[x1][y1];
						if (x1 < x && y1 > y + 1) {
							tmp[x1][y1] += tmp[x1 + 1][y1] + tmp[x1][y1 - 1] - tmp[x1 + 1][y1 - 1];
						} else if (x1 < x) {
							tmp[x1][y1] += tmp[x1 + 1][y1];
						} else if (y1 > y + 1) {
							tmp[x1][y1] += tmp[x1][y1 - 1];
						}
						sum3.add(tmp[x1][y1]);
					}
				}

				// left down dx,dy=(+, 1) range x+1 ~ N, 1 ~ y
				for (int x1 = x + 1; x1 <= N; x1++) {
					for (int y1 = y; y1 >= 1; y1--) {
						tmp[x1][y1] = arr[x1][y1];
						if (x1 > x + 1 && y1 < y) {
							tmp[x1][y1] += tmp[x1 - 1][y1] + tmp[x1][y1 + 1] - tmp[x1 - 1][y1 + 1];
						} else if (x1 > x + 1) {
							tmp[x1][y1] += tmp[x1 - 1][y1];
						} else if (y1 < y) {
							tmp[x1][y1] += tmp[x1][y1 + 1];
						}
						sum4.add(tmp[x1][y1]);
					}
				}

				// System.out.println(sum3);
				// System.out.println(sum4);
				for (int i : sum3) {
					for (int j : sum4) {
						if (i == j)
							answer++;
					}
				}

				// System.out.println("sum3.size() = " + sum3.size());
				// System.out.println("sum4.size() = " + sum4.size());

				// System.out.println("answer = " + answer);
				// System.out.println();
				sum1.clear();
				sum2.clear();
				sum3.clear();
				sum4.clear();
				
			}
		}
		System.out.println(answer);
	}
}