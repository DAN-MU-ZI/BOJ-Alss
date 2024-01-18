import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}

		int[] arr = new int[N];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			int newIdx = (idx + K - 1) % list.size();
			arr[i] = list.remove(newIdx);
			idx = newIdx;
		}
		String answer = "<" + Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(", ")) + ">";
		System.out.println(answer);
	}
}
