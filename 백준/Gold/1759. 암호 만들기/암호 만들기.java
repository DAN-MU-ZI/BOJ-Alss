import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');
	static int L;
	static int C;
	static char[] arr;
	static boolean[] visited;
	static int vowelCnt = 0;
	static int consonantCnt = 0;

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new char[C];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			arr[i] = st
				.nextToken()
				.charAt(0);
		}
		Arrays.sort(arr);

		visited = new boolean[C];

		func(0);
		System.out.println(sb);
	}

	private static void func(int depth) {
		if (depth == C) {
			if (vowelCnt > 0 && consonantCnt > 1 && vowelCnt + consonantCnt == L) {
				for (int i = 0; i < C; i++)
					if (visited[i])
						sb.append(arr[i]);
				sb.append("\n");
			}
			return;
		}

		visited[depth] = true;
		if (vowels.contains(arr[depth]))
			vowelCnt++;
		else
			consonantCnt++;
		func(depth + 1);
		
		visited[depth] = false;
		if (vowels.contains(arr[depth]))
			vowelCnt--;
		else
			consonantCnt--;
		func(depth + 1);
	}
}
