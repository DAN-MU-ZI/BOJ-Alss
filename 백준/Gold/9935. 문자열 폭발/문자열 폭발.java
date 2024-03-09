import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] charArray = br.readLine().toCharArray();
        char[] target = br.readLine().toCharArray();

        int length = charArray.length;

        int top = 0;
        int index = 0;

        while (index < length) {
            charArray[top++] = charArray[index];
            if (top >= target.length) {
                boolean is_boom = true;
                for (int i = 1; i <= target.length; i++) {
                    if (charArray[top - i] != target[target.length - i]) {
                        is_boom = false;
                        break;
                    }
                }
                if (is_boom) {
                    for (int i = 0; i < target.length; i++) {
                        top--;
                    }
                }
            }
            index++;
        }

        if (top == 0) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < top; i++) {
                sb.append(charArray[i]);
            }
            System.out.println(sb);
        }
    }
}