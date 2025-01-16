import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		Map<String, Boolean> map = new HashMap<>();
		map.put("ChongChong", true);
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String a  = st.nextToken();
			String b = st.nextToken();
			
			boolean aFlag = map.getOrDefault(a, false);
			boolean bFlag = map.getOrDefault(b, false);
			
			if (aFlag || bFlag) {
				map.put(a, true);
				map.put(b, true);
			} else {
				map.put(a, false);
				map.put(b, false);
			}
		}

		int answer = 0;
		for (String key: map.keySet()) {
			if (map.get(key)) answer++;
		}
		
		sb.append(answer);
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}

