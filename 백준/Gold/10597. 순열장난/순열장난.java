import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    private static boolean[] visited;
    private static Stack<Integer> stk = new Stack<>();
    private static boolean isReturn = false;
    private static char[] charArray;
    private static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        charArray = br.readLine().toCharArray();

        len = charArray.length < 10 ? charArray.length : (charArray.length - 9) / 2 + 9;
        visited = new boolean[len + 1];

        dfs(0);
        StringBuilder sb = new StringBuilder();
        for (int s : stk) {
            sb.append(s).append(" ");
        }
        System.out.println(sb);
    }

    private static void dfs(final int index) {
        if (isReturn) {
            return;
        }

        if (charArray.length - index == 0 && stk.size() == len) {
            isReturn = true;
            return;
        }

        int num = charArray[index] - '0';
        if (num == 0) {
            return;
        }

        if (num <= len && !visited[num]) {
            stk.push(num);
            visited[num] = true;

            dfs(index + 1);
            if (isReturn) {
                return;
            }

            visited[num] = false;
            stk.pop();
        }

        if (charArray.length - index > 1) {
            num *= 10;
            num += charArray[index + 1] - '0';

            if (num <= len && !visited[num]) {
                stk.push(num);
                visited[num] = true;

                dfs(index + 2);
                if (isReturn) {
                    return;
                }

                visited[num] = false;
                stk.pop();
            }
        }

    }
}