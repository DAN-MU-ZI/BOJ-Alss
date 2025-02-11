import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] pops = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            pops[i] = Integer.parseInt(st.nextToken());
        }

        int[][] edges = new int[N + 1][];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[] edge = new int[n];
            for (int j = 0; j < n; j++) {
                edge[j] = Integer.parseInt(st.nextToken());
            }
            edges[i] = edge;
        }

        int answer = Solution.solve(N, pops, edges);
        bw.write(String.valueOf(answer));

        bw.close();
        br.close();
    }

    static class Solution {
        static int N, answer;
        static int[] populations;
        static List<Integer>[] graph;
        static Map<Integer, Integer> A, comp, B;

        public static int solve(int n, int[]pops, int[][] edges) {
            init(n, pops, edges);

            simulate();

            return answer;
        }

        public static void simulate() {
            generateCombination();

            for (int mask: A.keySet()) {
                int popA = A.get(mask);
                if (popA == -1) continue;

                int rev = comp.get(mask);

                int popB = B.get(rev);
                if (popB == -1) continue;
                answer = Math.min(answer, Math.abs(popA- popB));
            }

            if (answer == Integer.MAX_VALUE) {
                answer = -1;
            }
        }

        static void bfs(int mask, int rev) {
            List<Integer> a = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if ((mask & (1 << i))> 0) a.add(i);
            }

            List<Integer> b = new ArrayList<>();
            for (int i = 1; i <= N; i++) {
                if ((rev & (1 << i))> 0) b.add(i);
            }

            A.put(mask, bfs(a, mask));
            B.put(rev, bfs(b, rev));
            comp.put(mask, rev);
        }

        static int bfs(List<Integer> nodes, int limit) {
            int s = nodes.get(0);

            boolean[] visited = new boolean[N + 1];
            Queue<Integer> q = new ArrayDeque<>();
            int p = 0;

            visited[s] = true;
            q.add(s);
            p += populations[s];

            while (!q.isEmpty()) {
                int node = q.poll();

                for (int nextNode: graph[node]) {
                    if (!visited[nextNode] && (limit & (1 << nextNode)) != 0) {
                        visited[nextNode] = true;
                        q.add(nextNode);
                        p += populations[nextNode];
                    }
                }
            }

            for (int i: nodes){
                if (!visited[i]) {
                    p = -1;
                    break;
                }
            }

            return p;
        }

        static void generateCombination() {
            A = new HashMap<>();
            B = new HashMap<>();
            comp = new HashMap<>();
            visited = 0;
            dfs(1, 0);
        }

        static int visited;
        public static void dfs(int idx, int size) {
            if (idx == N + 1){
                if (0 < size && size < N) {
                    int mask = visited;
                    int fullMask = ((1 << (N + 1)) - 2);
                    int rev = mask ^ fullMask;
                    
                    if (comp.containsKey(rev)) return;
                    bfs(mask, rev);
                }
                return;
            }

            visited |= (1 << idx);
            dfs(idx + 1, size + 1);
            
            visited &= ~(1 << idx);
            dfs(idx + 1, size);
        }

        public static void init(int n, int[]pops, int[][] edges) {
            N = n;
            populations = pops;
            
            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
                for (int v: edges[i]) {
                    graph[i].add(v);
                }
            }

            answer = Integer.MAX_VALUE;
        }
    }
}