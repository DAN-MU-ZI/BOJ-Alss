import java.util.Scanner;

public class Main {
	static final int DAY_TO_SECONDS = 86400;
	static final int HOUR_TO_SECONDS = 3600;
	static final int MINUTE_TO_SECONDS = 60;

	static int StringTimeToSeconds(String string) {
		String[] times = string.split(":");

		int timestamp;
		timestamp = Integer.parseInt(times[0]) * HOUR_TO_SECONDS;
		timestamp = timestamp + Integer.parseInt(times[1]) * MINUTE_TO_SECONDS;
		timestamp = timestamp + Integer.parseInt(times[2]);

		return timestamp;
	}

	static String SecondsToStringTime(int seconds) {
		int HH = seconds / HOUR_TO_SECONDS;
		int MM = seconds / MINUTE_TO_SECONDS % MINUTE_TO_SECONDS;
		int SS = seconds % MINUTE_TO_SECONDS;
		return String.format("%02d:%02d:%02d", HH, MM, SS);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String startTime = scanner.nextLine();
		String endTime = scanner.nextLine();

		int startSeconds = StringTimeToSeconds(startTime);
		int endSeconds = StringTimeToSeconds(endTime);

		int timeDiff = endSeconds - startSeconds;
		if (timeDiff <= 0) {
			timeDiff = timeDiff + DAY_TO_SECONDS;
		}
		String answer = SecondsToStringTime(timeDiff);
		System.out.println(answer);
	}
}
