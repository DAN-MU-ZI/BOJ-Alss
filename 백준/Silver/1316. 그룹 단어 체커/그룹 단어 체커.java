import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            int[] alp_di = new int[26];
            boolean is_dup = false;
            Character prev = null;
            for (char c : br.readLine().toCharArray()) {
                if (prev != null && prev != c) {
                    alp_di[prev - 'a'] = 1;
                    if (alp_di[c - 'a'] != 0) {
                        is_dup = true;
                        break;
                    }
                }
                prev = c;
            }
            if (!is_dup) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}