import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s = scanner.next().toUpperCase();
        char[] charArray = s.toCharArray();
        int[] charCounter = new int[26];

        for (char c : charArray) {
            charCounter[c - 65]++;
        }

        char modeChar = 'A';
        boolean isDuplicated = false;
        for (int j = 1; j < charCounter.length; j++) {
            if (charCounter[j] > charCounter[modeChar - 65]) {
                modeChar = (char) (j + 65);
                isDuplicated = false;
            } else if (charCounter[j] == charCounter[modeChar - 65]) {
                isDuplicated = true;
            }
        }

        if (isDuplicated) {
            System.out.println("?");
        } else {
            System.out.println(modeChar);
        }

    }
}
