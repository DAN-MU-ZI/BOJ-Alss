import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int M = Integer.parseInt(br.readLine());
		int bitmask = 0;

		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();

			if (command.equals("add")) {
				int x = Integer.parseInt(st.nextToken());
				bitmask |= (1 << x);
			} else if (command.equals("remove")) {
				int x = Integer.parseInt(st.nextToken());
				bitmask &= ~(1 << x);
			} else if (command.equals("check")) {
				int x = Integer.parseInt(st.nextToken());
				sb.append((bitmask & (1 << x)) != 0 ? "1\n" : "0\n");
			} else if (command.equals("toggle")) {
				int x = Integer.parseInt(st.nextToken());
				bitmask ^= (1 << x);
			} else if (command.equals("all")) {
				bitmask = (1 << 21) - 1;
			} else if (command.equals("empty")) {
				bitmask = 0;
			}
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}