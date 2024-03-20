import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] graph;
    static boolean[] visited;
    static Stack<Integer> result;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        graph = new List[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }
        for (int i = 0; i < n + 1; i++) {
            graph[i].sort(Integer::compareTo);
        }

        visited = new boolean[n + 1];
        result = new Stack<>();
        dfs(start);
        printResult();

        visited = new boolean[n + 1];
        result = new Stack<>();
        bfs(start);
        printResult();
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static void dfs(final int node) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;
        result.push(node);

        for (int nextNode : graph[node]) {
            if (!visited[nextNode]) {
                dfs(nextNode);
            }
        }
    }

    private static void bfs(final int start) {
        Deque<Integer> deque = new ArrayDeque<>();
        int cnt = 0;

        deque.push(start);
        result.push(start);
        cnt++;
        visited[start] = true;

        while (!deque.isEmpty() && cnt != n) {
            int node = deque.pollLast();

            for (int nextNode : graph[node]) {
                if (!visited[nextNode]) {
                    deque.push(nextNode);
                    visited[nextNode] = true;
                    cnt++;
                    result.push(nextNode);
                }
            }
        }
    }
}