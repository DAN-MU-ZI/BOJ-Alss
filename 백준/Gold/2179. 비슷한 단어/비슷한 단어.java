import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}

		int idx1 = -1, idx2 = -1;
		int max = 0;

		for (int i = 0; i < N - 1; i++) {
			String s1 = arr[i];

			for (int j = i + 1; j < N; j++) {
				String s2 = arr[j];
				int dup = calc(s1, s2);

				if (dup > max) {
					idx1 = i;
					idx2 = j;
					max = dup;
				}
			}
		}

		System.out.println(arr[idx1]);
		System.out.println(arr[idx2]);
		br.close();
	}

	static int calc(String a, String b) {
		int len = 0;
		while (len < a.length() && len < b.length() && a.charAt(len) == b.charAt(len)) {
			len++;
		}
		return len;
	}
}
