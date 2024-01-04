import java.util.Scanner;

public class Main {
	static int StringTimeToSeconds(String string) {
		String[] times = string.split(":");

		int timestamp;
		timestamp = Integer.parseInt(times[0]);
		timestamp = timestamp * 60 + Integer.parseInt(times[1]);
		timestamp = timestamp * 60 + Integer.parseInt(times[2]);

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
