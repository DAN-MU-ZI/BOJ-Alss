import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int row = 0;
        boolean[] col = new boolean[n];
        boolean[] right_up = new boolean[2 * n + 1];
        boolean[] left_down = new boolean[2 * n + 1];

        dfs(n, row, col, right_up, left_down);
        System.out.println(answer);
    }

    private static void dfs(final int target, final int row, final boolean[] col, final boolean[] rightUp,
                            final boolean[] leftDown) {
        if (row == target) {
            answer++;
            return;
        }

        for (int i = 0; i < target; i++) {
            int leftIdx = i + row;
            int rightIdx = i - row + target;

            if (col[i] || rightUp[rightIdx] || leftDown[leftIdx]) {
                continue;
            }

            col[i] = true;
            rightUp[rightIdx] = true;
            leftDown[leftIdx] = true;
            dfs(target, row + 1, col, rightUp, leftDown);
            col[i] = false;
            rightUp[rightIdx] = false;
            leftDown[leftIdx] = false;
        }
    }
}