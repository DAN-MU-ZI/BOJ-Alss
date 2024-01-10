import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		Map<Integer, Integer> map = new HashMap<>();
		List<Integer> arr = new ArrayList<>();
		List<Integer> orderedArr = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			arr.add(num);
			orderedArr.add(num);
			if (map.containsKey(num)) {
				map.replace(num, map.get(num) + 1);
			} else {
				map.put(num, 1);
			}
		}

		arr.sort((o1, o2) -> {
			if (Objects.equals(map.get(o1), map.get(o2))) {
				return orderedArr.indexOf(o1) - orderedArr.indexOf(o2);
			} else {
				return Integer.compare(map.get(o2), map.get(o1));
			}
		});

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(arr.get(i)).append(" ");
		}
		System.out.println(sb);
	}
}