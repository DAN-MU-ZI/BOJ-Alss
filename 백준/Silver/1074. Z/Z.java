import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int r;
    private static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, n);

        int row = 0, col = 0;
        int answer = 0;
        while (!(row == r && col == c)) {
//            System.out.println("row = " + row);
//            System.out.println("col = " + col);
//            System.out.println("answer = " + answer);
            size /= 2;

            boolean rowCon = r >= row + size;
            boolean colCon = c >= col + size;

            if (rowCon && colCon) {
                row += size;
                col += size;
                answer += size * size * 3;
            } else if (rowCon && !colCon) {
                row += size;
                answer += size * size * 2;
            } else if (!rowCon && colCon) {
                col += size;
                answer += size * size;
            }
        }

        System.out.println(answer);
    }
}