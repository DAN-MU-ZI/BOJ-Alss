import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class Main {
    static int StringTimeToSeconds(String string) {
        List<Integer> times = Arrays.stream(string.split(":"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int timestamp;
        timestamp = times.get(0);
        timestamp = timestamp * 60 + times.get(1);
        timestamp = timestamp * 60 + times.get(2);

        return timestamp;
    }

    static String SecondsToStringTime(int seconds) {
        int HH = seconds / (60 * 60);
        int MM = seconds / 60 % 60;
        int SS = seconds % 60;
        return String.format("%02d:%02d:%02d", HH, MM, SS);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String startTime = scanner.nextLine();
        String endTime = scanner.nextLine();

        int startSeconds = StringTimeToSeconds(startTime);
        int endSeconds = StringTimeToSeconds(endTime);

        String string;
        if (endSeconds > startSeconds) {
            string = SecondsToStringTime(endSeconds - startSeconds);
        } else {
            string = SecondsToStringTime(endSeconds + (24 * 60 * 60) - startSeconds);
        }
        System.out.println(string);
    }
}
