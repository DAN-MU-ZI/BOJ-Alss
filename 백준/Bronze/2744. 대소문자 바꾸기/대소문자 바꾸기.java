import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int CASE_SHIFT_SIZE = 32;

        String s = in.nextLine();
        char[] charArray = s.toCharArray();
        for(int i=0;i<charArray.length;i++){
            if (charArray[i]<'a'){
                charArray[i]+= CASE_SHIFT_SIZE;
            }else {
                charArray[i]-= CASE_SHIFT_SIZE;
            }
        }
        System.out.println(String.valueOf(charArray));
    }
}
