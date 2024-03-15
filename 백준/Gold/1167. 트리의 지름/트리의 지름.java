import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int v;
    final static Map<Integer, Map<Integer, Integer>> arr = new HashMap<>();
    static int A, B;
    static int distance;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());

        for (int i = 0; i < v + 1; i++) {
            arr.put(i, new HashMap<>());
        }
        visited = new boolean[v + 1];

        for (int i = 1; i <= v; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int curNode = Integer.parseInt(st.nextToken());
            while (true) {
                int nextNode = Integer.parseInt(st.nextToken());
                if (nextNode == -1) {
                    break;
                }
                int distance = Integer.parseInt(st.nextToken());
                arr.get(curNode).put(nextNode, distance);
            }
        }

        visited[1] = true;
        dfs(1, 0);
        visited[1] = false;

        visited[B] = true;
        dfs(B, 0);
        System.out.println(distance);
    }

    private static void dfs(final int curNode, final int accDistance) {
        boolean isEnd = true;

        Map<Integer, Integer> childNodes = arr.get(curNode);

        for (int nextNode : childNodes.keySet()) {
            if (visited[nextNode]) {
                continue;
            }

            visited[nextNode] = true;

            dfs(nextNode, accDistance + childNodes.get(nextNode));
            isEnd = false;

            visited[nextNode] = false;
        }

        if (isEnd) {
            if (distance < accDistance) {
                distance = accDistance;
                B = curNode;
            }
        }
    }
}