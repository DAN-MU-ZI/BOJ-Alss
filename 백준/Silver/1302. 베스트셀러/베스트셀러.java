import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Main {


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n =Integer.parseInt(br.readLine());
		TreeMap<String,Integer> map = new TreeMap<>(String::compareTo);
		for(int i=0;i<n;i++){
			String book = br.readLine();
			if(!map.containsKey(book)){
				map.put(book,1);
			} else{
				map.replace(book,map.get(book)+1);
			}
		}

		Comparator<Map.Entry<String, Integer>> comparator = Map.Entry.comparingByValue();
		Map.Entry<String,Integer> maxEntry = Collections.max(map.entrySet(), comparator);

		System.out.println(maxEntry.getKey());
	}
}