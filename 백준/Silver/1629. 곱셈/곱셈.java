import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int a;
    static int b;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        System.out.println(dfs(a, b));
    }

    private static long dfs(final int x, final int y) {
        if (y == 1) {
            return x % c;
        }

        long res = dfs(x, y / 2);
        if (y % 2 == 1) {
            return (res * res % c) * x % c;
        }

        return (res * res) % c;
    }
}