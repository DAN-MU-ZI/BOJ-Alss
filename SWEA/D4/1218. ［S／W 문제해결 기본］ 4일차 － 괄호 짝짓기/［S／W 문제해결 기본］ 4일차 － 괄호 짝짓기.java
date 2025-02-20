import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static char[] line;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        final int T = 10;
        for (int t = 1; t <= T; t++) {            
            N = Integer.parseInt(br.readLine());
            line = br.readLine().trim().toCharArray();

            int answer = solve();
            sb.append(String.format("#%d %s\n", t, answer));
        }
        
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int solve() {
        Stack<Character> stk  = new Stack<>();

        for (int i = N - 1; i >= 0; i--) {
            char c = line[i];

            if (stk.isEmpty()) {
                stk.push(c);
                continue;
            }
            
            if (c == ')' || c == '}' || c == ']' || c == '>') {
                stk.push(c);
            } else {
                if (
                    c == '(' && stk.peek() != ')'
                    || c == '[' && stk.peek() != ']'
                    || c == '{' && stk.peek() != '}'
                    || c == '<' && stk.peek() != '>'
                ) {
                    return 0;
                } else {
                    stk.pop();
                }
            }
        }

        if (!stk.isEmpty()) return 0;
        
        return 1;
    }
}