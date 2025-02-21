import java.io.*;
import java.util.*;

class Solution {
    static int N, L;
    static int[][] ingredients;
    static int answer;

	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            ingredients = new int[2][N];
            answer = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                ingredients[0][i] = Integer.parseInt(st.nextToken());
                ingredients[1][i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0, 0);
            
			sb.append(String.format("#%d %d\n", test_case, answer));
		}
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}

    static void dfs(int idx, int score, int calorie) {
        if (idx == N) {
            answer = Math.max(answer, score);
            return;
        }

        int newCalorie = calorie + ingredients[1][idx];
        if (newCalorie <= L) {
            dfs(idx + 1, score + ingredients[0][idx], newCalorie);
        }
        
        dfs(idx + 1, score, calorie);
    }
}