import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        Solution solution = new Solution();
        int N = Integer.parseInt(br.readLine());
        int result = solution.solve(N);
        
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
	}   
}

class Solution {
	public int solve(int N) {
		int answer = N * (N - 1);

		return answer;
	}
}