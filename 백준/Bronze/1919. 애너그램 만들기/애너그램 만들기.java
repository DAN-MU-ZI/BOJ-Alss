import java.util.Scanner;

import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();

        int CASE_SHIFT = 97;
        int ALPHA_LEN = 26;

        int[] charCnt1 = new int[ALPHA_LEN];
        int[] charCnt2 = new int[ALPHA_LEN];

        for (int i = 0; i < s1.length(); i++) {
            charCnt1[s1.charAt(i) - CASE_SHIFT] ++;
        }
        for (int j = 0; j < s2.length(); j++) {
            charCnt2[s2.charAt(j) - CASE_SHIFT] ++;
        }


        int answer = 0;
        for (int k = 0; k < ALPHA_LEN; k++) {
            int diff = charCnt1[k] - charCnt2[k];
            answer += abs(diff);
        }

        System.out.println(answer);
    }
}
