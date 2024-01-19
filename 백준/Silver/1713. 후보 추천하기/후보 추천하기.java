import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		List<Student> students = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int id = Integer.parseInt(st.nextToken());
			boolean isVoted = false;
			for (Student student : students) {
				if (student.id == id) {
					student.vote++;
					isVoted = true;
					break;
				}
			}

			if (!isVoted) {
				if (students.size() < N)
					students.add(0, new Student(id, 1, i));
				else {
					students.sort((o1, o2) -> {
						if (o1.vote == o2.vote) {
							return o1.turn - o2.turn;
						}
						return o1.vote - o2.vote;
					});
					students.set(0, new Student(id, 1, i));
				}
			}
		}

		students.sort(Comparator.comparingInt(o -> o.id));
		System.out.println(
			students.stream()
				.map(x -> String.valueOf(x.id))
				.collect(Collectors.joining(" ")));
	}
}

class Student {
	int id;
	int vote;
	int turn;

	public Student(int id, int vote, int turn) {
		this.id = id;
		this.vote = vote;
		this.turn = turn;
	}
}
