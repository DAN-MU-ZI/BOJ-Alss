import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			sb
				.append(func(br))
				.append("\n");
		}
		System.out.println(sb);
	}

	private static int func(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
		LinkedList<Document> documents = new LinkedList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int priority = Integer.parseInt(st.nextToken());

			queue.offer(priority);
			documents.offer(new Document(i, priority));
		}

		int count = 1;
		while (!queue.isEmpty() && !documents.isEmpty()) {
			Document document = documents.poll();
			if (document.priority == queue.peek()) {
				if (document.index == M)
					return count;
				else {
					queue.poll();
					count++;
				}
			} else {
				documents.addLast(document);
			}
		}
		return -1;
	}
}

class Document {
	int index;
	int priority;

	public Document(int index, int priority) {
		this.index = index;
		this.priority = priority;
	}
}