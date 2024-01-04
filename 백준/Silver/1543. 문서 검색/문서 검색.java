import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        String word = scanner.nextLine();

        int idx = 0;
        int answer = 0;
        while (idx <= (str.length() - word.length())) {
            if (str.charAt(idx) == word.charAt(0)) {
                boolean equalFlag = true;
                for (int i = 0; i < word.length(); i++) {
                    if (str.charAt(idx + i) != word.charAt(i)) {
                        equalFlag = false;
                        break;
                    }
                }
                if (equalFlag) {
                    answer++;
                    idx = idx + word.length() - 1;
                }
            }
            idx++;
        }
        System.out.println(answer);
    }
}
