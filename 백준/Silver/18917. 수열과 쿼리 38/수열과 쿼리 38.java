import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		TreeSet<Integer> arr = new TreeSet<>();
		arr.add(0);

		long total = 0;
		long xor = 0;
		int N = Integer.parseInt(br.readLine());
		while (N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());

			if (command == 1) {
				int x = Integer.parseInt(st.nextToken());
				arr.add(x);
				total += x;
				xor ^= x;
			} else if (command == 2) {
				int x = Integer.parseInt(st.nextToken());
				arr.remove(x);
				total -= x;
				xor ^= x;
			} else if (command == 3) {
				sb.append(total).append("\n");
			} else if (command == 4) {
				sb.append(xor).append("\n");
			}
		}
		System.out.println(sb);

	}
}
