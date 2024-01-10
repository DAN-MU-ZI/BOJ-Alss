import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		TreeMap<String, String> history = new TreeMap<>(Comparator.reverseOrder());
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			history.put(st.nextToken(), st.nextToken());
		}

		StringBuilder sb = new StringBuilder();
		for (String s : history.keySet()) {
			if (history.get(s).equals("enter")) {
				sb.append(s).append("\n");
			}
		}
		bw.write(String.valueOf(sb));
		bw.close();
	}
}
