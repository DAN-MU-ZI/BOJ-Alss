import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int d;
    static boolean[] slots;
    static int[] number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        slots = new boolean[d];
        number = new int[d];
        System.out.println(find(0));
    }

    private static int find(final int depth) {
        if (depth == d) {
            int res = 0;
            for (int i : number) {
                res *= d;
                res += i;
            }
            return res;
        }

        int s = 0;
        if (depth == 0) {
            s = 1;
        }
        for (int i = s; i < d; i++) {
            if (!slots[i]) {
                slots[i] = true;
                number[depth] = i;
                int num = find(depth + 1);
                if (num > n) {
                    return num;
                }

                slots[i] = false;
            }
        }
        return -1;
    }


}