import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int M = Integer.parseInt(br.readLine());
		boolean[] mask = new boolean[1 << 21 + 1];

		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();

			if (command.equals("all")) {
				for (int j = 1; j < 21; j++) {
					mask[1 << j] = true;
				}
				continue;
			}

			if (command.equals("empty")) {
				mask = new boolean[1 << 21 + 1];
				continue;
			}

			int x = Integer.parseInt(st.nextToken());
			if (command.equals("add")) {
				mask[1 << x] = true;
			} else if (command.equals("remove")) {
				mask[1 << x] = false;
			} else if (command.equals("check")) {
				if (mask[1 << x]) sb.append("1\n");
				else sb.append("0\n");
			} else if (command.equals("toggle")) {
				mask[1 << x] = !mask[1 << x];
			}

		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}