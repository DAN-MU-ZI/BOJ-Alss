import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        String word = scanner.nextLine();

        int idx = 0;
        int answer = 0;
        while (idx <= (str.length() - word.length())) {
            int findIdx =str.indexOf(word, idx);
            if(findIdx == -1){
                break;
            }

            idx = findIdx+word.length();
            answer++;
        }
        System.out.println(answer);
    }
}
