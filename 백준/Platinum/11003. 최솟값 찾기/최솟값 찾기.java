import java.io.*;
import java.util.*;

public class Main {
	
	static int N, L;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		

		ArrayDeque<int[]> dq = new ArrayDeque<>();
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			while(!dq.isEmpty() && dq.peekLast()[0] > num) {
				dq.pollLast();
			}
			dq.add(new int[] {num, i});
			if (dq.peek()[1] < i - (L - 1)) {
				dq.poll();
			}
			sb.append(dq.peek()[0] + " ");
		}
		
		
		System.out.println(sb);
	}
}
