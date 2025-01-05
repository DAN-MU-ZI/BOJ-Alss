import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		String[] channels = new String[N];
		int kbs1Index = 0, kbs2Index = 0;

		for (int i = 0; i < N; i++) {
			channels[i] = br.readLine();
			if (channels[i].equals("KBS1")) kbs1Index = i;
			if (channels[i].equals("KBS2")) kbs2Index = i;
		}

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < kbs1Index; i++) {
			result.append(1);
		}
		for (int i = 0; i < kbs1Index; i++) {
			result.append(4);
		}

		if (kbs2Index < kbs1Index) {
			kbs2Index++;
		}

		for (int i = 0; i < kbs2Index; i++) {
			result.append(1);
		}
		for (int i = 0; i < kbs2Index - 1; i++) {
			result.append(4);
		}

		bw.write(result.append("\n").toString());
		bw.flush();
		bw.close();
	}
}
