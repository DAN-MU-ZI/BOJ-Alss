import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Deque<String> queue = new LinkedList<>();

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			String[] split = br
				.readLine()
				.split(" ");

			switch (split[0]) {
				case "push":
					queue.addLast(split[1]);
					break;
				case "pop":
					if (queue.isEmpty())
						sb
							.append(-1)
							.append("\n");
					else
						sb
							.append(queue.pop())
							.append("\n");
					break;
				case "size":
					sb
						.append(queue.size())
						.append("\n");
					break;
				case "empty":
					sb
						.append(queue.isEmpty() ? 1 : 0)
						.append("\n");
					break;
				case "front":
					if (queue.isEmpty())
						sb
							.append(-1)
							.append("\n");
					else
						sb
							.append(queue.getFirst())
							.append("\n");
					break;
				case "back":
					if (queue.isEmpty())
						sb
							.append(-1)
							.append("\n");
					else
						sb
							.append(queue.getLast())
							.append("\n");
					break;
			}
		}
		System.out.println(sb);
	}
}