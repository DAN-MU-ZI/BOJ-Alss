import java.io.*;
import java.util.*;

class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Comparator<String> comparator = (s1, s2) -> s1.compareTo(s2);
        Map<String, Integer> map = new HashMap<>();

        while (N-- > 0) {
            String input = br.readLine().strip();
            int count = map.getOrDefault(input, 0);
            if (input.length() >= M) map.put(input, count+1);
        }

        List<String> keySet = new ArrayList<>(map.keySet());
        keySet.sort((s1, s2) -> {
            int count = map.get(s2) - map.get(s1);

            if (count != 0) {
                return count;
            }

            int lengthCompare = s2.length() - s1.length();
            
            if (lengthCompare != 0) {
                return lengthCompare;
            }

            return s1.compareTo(s2);
        });

        for (String key: keySet) {
            sb.append(key).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}