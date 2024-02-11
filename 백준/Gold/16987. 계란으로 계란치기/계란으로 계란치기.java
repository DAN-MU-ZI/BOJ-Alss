import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int S;
	static Egg[] arr;
	static boolean[] visited;

	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		arr = new Egg[N];
		visited = new boolean[N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int durability = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			Egg egg = new Egg(durability, weight);

			arr[i] = egg;
		}

		func(0, 0);
		System.out.println(answer);
	}

	private static void func(int depth, int sum) {
		if (depth == N) {
			answer = Math.max(answer, sum);
			return;
		}
		Egg hand = arr[depth];
		for (int i = 0; i < N; i++) {
			Egg next = arr[i];
			if (i == depth) {
				continue;
			}

			boolean is_hand_reverse = false;
			boolean is_next_reverse = false;
			boolean is_crashed = false;
			if (hand.durability > 0 && next.durability > 0) {
				is_crashed = true;
				if (hand.durability - next.weight <= 0) {
					sum++;
					is_hand_reverse = true;
				}
				if (next.durability - hand.weight <= 0) {
					sum++;
					is_next_reverse = true;
				}
				hand.durability = hand.durability - next.weight;
				next.durability = next.durability - hand.weight;
			}

			func(depth + 1, sum);

			if (is_crashed) {
				if (is_hand_reverse)
					sum--;
				if (is_next_reverse)
					sum--;

				hand.durability = hand.durability + next.weight;
				next.durability = next.durability + hand.weight;
			}
		}
	}
}

class Egg {
	int durability;
	int weight;

	public Egg(int durability, int weight) {
		this.durability = durability;
		this.weight = weight;
	}
}