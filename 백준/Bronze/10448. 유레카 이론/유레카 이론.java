import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		int[] table = new int[45];
		for (int i = 1; i <= 44; i++) {
			table[i] = table[i - 1] + i;
		}

		boolean[] flagTable = new boolean[1001];
		for (int i = 1; i < table.length; i++) {
			for (int j = 1; j < table.length; j++) {
				for (int k = 1; k < table.length; k++) {
					int sum = table[i] + table[j] + table[k];
					if (sum <= 1000)
						flagTable[sum] = true;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < t; j++) {
			int n = Integer.parseInt(br.readLine());
			sb.append(flagTable[n] ? 1 : 0).append("\n");
		}
		System.out.println(sb);
	}
}
