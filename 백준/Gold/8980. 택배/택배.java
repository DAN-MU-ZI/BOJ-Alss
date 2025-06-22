import java.io.*;
import java.util.*;

public class Main {
	static class Box implements Comparable<Box> {
		int from, to, num;
		public Box(int _from, int _to, int _num) {
			from = _from;
			to = _to;
			num = _num;
		}

		public int compareTo(Box o) {
			if(to == o.to) return from - o.from;
			return to - o.to;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		Box[] boxes = new Box[m];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			boxes[i] = new Box(from, to, num);
		}

		Arrays.sort(boxes);

		int[] truck = new int[n + 1];
		int answer = 0;

		for (Box box : boxes) {
			int maxLoad = 0;

			for (int i = box.from; i < box.to; i++) {
				maxLoad = Math.max(maxLoad, truck[i]);
			}
			int canLoad = Math.min(c - maxLoad, box.num);
			if (canLoad <= 0) continue;
			
			for (int i = box.from; i < box.to; i++) {
				truck[i] += canLoad;
			}
			answer += canLoad;
		}
		System.out.println(answer);
	}
}
