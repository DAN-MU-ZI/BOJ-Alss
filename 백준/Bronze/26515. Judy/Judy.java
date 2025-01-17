import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		Solution solution = new Solution();

		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			List<Integer> arr = new ArrayList<>();
			while (st.hasMoreTokens()) {
				arr.add(Integer.parseInt(st.nextToken()));
			}
			StringBuilder result = solution.solve(arr);
			sb.append(result).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	private static class Solution {
		Solution() {
		}

		public StringBuilder solve(List<Integer> arr) {
			StringBuilder sb = new StringBuilder();
			for (int num : arr) {
				if ('a' <= num && num <= 'z') {
					sb.append((char)num);
				} else if ('A' <= num && num <= 'Z') {
					sb.append((char)(num + 32));
				} else {
					sb.append('-');
				}
			}
			char c = sb.charAt(0);
			sb.deleteCharAt(0);
			sb.append(c);
			return sb.append("ay");
		}
	}
}