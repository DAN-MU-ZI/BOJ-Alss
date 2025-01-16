import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		// 산술 평균
		int sum = Arrays.stream(arr).sum();
		int mean = Integer.parseInt(String.format("%.0f", (double)sum / N));
		sb.append(mean).append("\n");
		
		// 중앙값
		Arrays.sort(arr);
		int median = arr[N / 2];
		sb.append(median).append("\n");
		
		// 최빈값
		Map<Integer, Integer> map = new HashMap<>();
		int maxCount = 0;
		for (int i = 0; i < N; i++) {
			int nextCount = map.getOrDefault(arr[i], 0) + 1;
			map.put(arr[i], nextCount);
			maxCount = Math.max(maxCount, nextCount);
		}
		
		int minInModes = Integer.MAX_VALUE;
		List<Integer> modes = new ArrayList<>();
		for (Integer key: map.keySet()) {
			if (map.get(key) != maxCount) continue;
			
			minInModes = Math.min(minInModes, key);
			modes.add(key);
		}
		
		modes.sort((s1, s2) -> s1 - s2);
		
		int mode;
		if (modes.size() == 1) mode = modes.get(0);
		else mode = modes.get(1);
		
		sb.append(mode).append("\n");
		
		// 범위
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			int num = arr[i];
			max = Math.max(max, num);
			min = Math.min(min, num);
		}
		sb.append(Math.abs(max - min)).append("\n");
		
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}

