import java.io.*;
import java.util.*;

class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
        int T = Integer.parseInt(br.readLine());
        Solution solution;
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            solution = new Solution(N, arr);
            sb.append(solution.solve()).append("\n");
        }

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

    private static class Union {
        private final int[] nodes;

        public Union(int n) {
            nodes = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                nodes[i] = i;
            }
        }

        public int find(int n) {
            if (nodes[n] != n) {
                nodes[n] = find(nodes[n]);
            }
            return nodes[n];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX < rootY) {
                nodes[rootY] = rootX;
            } else {
                nodes[rootX] = rootY;
            }
        }

        public int[] getNodes() {
            return this.nodes;
        }
    }

    private static class Solution {
        private final int N;
        private final int[] arr;

        public Solution(int N, int[] arr) {
            this.N = N;
            this.arr = arr;
        }

        public int solve() {
            Union union = new Union(N);
            
            for (int i = 1; i <= N; i++) {
                union.union(i, arr[i- 1]);
            }

            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                set.add(union.find(i));
            }

            return set.size();
        }
    }
}
