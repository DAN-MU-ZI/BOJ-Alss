import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Map<String, Boolean> map = null;
		int answer = 0;
		while(N-- > 0) {
			String s = br.readLine().strip();
			if (s.equals("ENTER")) map = new HashMap<>();
			else {
				if (map.getOrDefault(s, null) == null) answer++;
				map.put(s, false);
			}
		}

		sb.append(answer);
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}

