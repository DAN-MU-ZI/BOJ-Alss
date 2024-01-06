import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.ElementType;
import java.util.StringTokenizer;
import java.util.concurrent.CountDownLatch;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		char[] dict = new char[36];
		for (int i = 0; i < 10; i++) {
			dict[i] = (char)('0' + i);
		}
		for (char c = 'A'; c <= 'Z'; c++) {
			dict[c - 'A' + 10] = c;
		}

		StringBuilder sb = new StringBuilder();
		while(N>0){
			sb.append(dict[N%B]);
			N=N/B;
		}
		System.out.println(sb.reverse().toString());
	}
}
