import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[][] map = new int[n][2];
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			map[i][0] = s;
			map[i][1] = e;
		}

		Arrays.sort(map, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0]) {
					return o1[1] - o2[1];
				} else {
					return o1[0] - o2[0];
				}
			}
		});

		int start, end;
		start = map[0][0];
		end = map[0][1];

		int answer = 1;
		for (int i = 1; i < n; i++) {
			int s = map[i][0];
			int e = map[i][1];
			if (start < s && s < end && e <= end) {
				start = s;
				end = e;
			} else if (end <= s) {
				answer++;
				start = s;
				end = e;
			}
		}
		System.out.println(answer);
	}
}