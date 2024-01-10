import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];
		Set<Integer> set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			Integer num = Integer.parseInt(st.nextToken());
			set.add(num);
			arr[i] = num;
		}
		int[] sortedArr = set.stream().mapToInt(Integer::intValue).toArray();
		Arrays.sort(sortedArr);
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int i = 0; i < sortedArr.length; i++) {
			map.put(sortedArr[i], i);
		}

		String[] answer = new String[n];
		for (int i = 0; i < n; i++) {
			answer[i] = String.valueOf(map.get(arr[i]));
		}
		System.out.println(String.join(" ", answer));
	}
}