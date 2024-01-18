import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Set<String> canNotListeners = new HashSet<>();
		for (int i = 0; i < N; i++) {
			canNotListeners.add(br.readLine());
		}

		int counter = 0;
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			String input = br.readLine();
			if (canNotListeners.contains(input)) {
				counter++;
				list.add(input);
			}
		}

		System.out.println(counter);
		list.sort(String::compareTo);
		list.forEach(System.out::println);
	}
}