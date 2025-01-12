import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String N = br.readLine();
		int length = N.length();
		int[] arr = new int[length];
		for (int i = 0; i < length; i ++) {
			arr[i] = N.charAt(i) - '0';
		}
		Arrays.sort(arr);

		StringBuilder sb = new StringBuilder();
		for (int i = length - 1; i > -1; i--) {
			sb.append(arr[i]);
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}