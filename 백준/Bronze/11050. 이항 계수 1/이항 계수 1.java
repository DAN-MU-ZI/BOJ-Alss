import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Solution solution = new Solution();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int result = solution.solve(N,  K);
        
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
	}   
}

class Solution {
	public int solve(int N, int K) {
		int answer = 1;
		for(int n = N; n > N - K; n--) {
			answer *= n;
		}
		for (int k = 1; k <= K; k++) {
			answer /= k;
		}
		return answer;
	}
}