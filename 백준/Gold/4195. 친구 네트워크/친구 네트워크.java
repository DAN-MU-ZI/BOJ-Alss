import java.io.*;
import java.util.*;

class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        Solution solution;
        for (int t = 0; t < T; t++) {
            int F = Integer.parseInt(br.readLine());
            String[][] input = new String[F][2];
            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                input[i] = new String[] {a, b};
            }
            solution = new Solution(F, input);
            int[] answer = solution.solve();
            for (int ans: answer) {
                sb.append(ans).append("\n");
            }
        }

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

    private static class Union {
        Map<String, Integer> friends = new HashMap();
        Map<String, String> parent = new HashMap();

        public void put(String a) {
            if (parent.getOrDefault(a, null) == null) {
                parent.put(a, a);
                friends.put(a, 1);
            }
        }

        public String find(String a) {
            if (!a.equals(parent.get(a))){
                return find(parent.get(a));
            }

            return a;
        }

        public void union(String a, String b) {
            String rootX = find(a);
            String rootY = find(b);

            int cmp = rootX.compareTo(rootY);
            if (cmp < 0) {
                parent.put(rootY, parent.get(rootX));
                friends.put(rootX, friends.get(rootX) + friends.get(rootY));
            } else if (cmp > 0) {
                parent.put(rootX, parent.get(rootY));
                friends.put(rootY, friends.get(rootY) + friends.get(rootX));
            }
        }

        public int getSize(String a) {
            return friends.get(parent.get(find(a)));
        }
    }

	private static class Solution {
        private final int F;
        private final String[][] input;

        public Solution(int F, String[][] input) {
            this.F = F;
            this.input = input;
        }

        public int[] solve() {
            int[] answer = new int[F];
            Union union = new Union();
            for (int i = 0; i < F; i++) {
                String a = input[i][0];
                String b = input[i][1];

                union.put(a);
                union.put(b);

                union.union(a, b);
                answer[i] = union.getSize(a);
            }
            return answer;
        }
    }       
}
