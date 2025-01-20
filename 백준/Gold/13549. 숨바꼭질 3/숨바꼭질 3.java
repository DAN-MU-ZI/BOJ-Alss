import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        Solution solution = new Solution(N, K);
        int answer = solution.solve();
        sb.append(answer);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

class Solution {
    private final int N;
    private final int K;

    public Solution(int N, int K) {
        this.N = N;
        this.K = K;
    }

    public int solve() {
        int[] visited = new int[100001];
        Arrays.fill(visited, Integer.MAX_VALUE);

        Queue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));

        pq.add(new Node(N,0));
        visited[N] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > visited[cur.pos]) continue;

            int nextPos, nextCost;

            nextPos = cur.pos - 1;
            nextCost = cur.cost + 1;
            if (nextPos >= 0 && nextPos < 100001 && nextCost < visited[nextPos]) {
                visited[nextPos] = nextCost;
                pq.add(new Node(nextPos, nextCost));
            }

            nextPos = cur.pos - 1;
            nextCost = cur.cost + 1;
            if (nextPos >= 0 && nextPos < 100001 && nextCost < visited[nextPos]) {
                visited[nextPos] = nextCost;
                pq.add(new Node(nextPos, nextCost));
            }

            nextPos = cur.pos + 1;
            nextCost = cur.cost + 1;
            if (nextPos >= 0 && nextPos < 100001 && nextCost < visited[nextPos]) {
                visited[nextPos] = nextCost;
                pq.add(new Node(nextPos, nextCost));
            }

            nextPos = cur.pos * 2;
            nextCost = cur.cost;
            if (nextPos >= 0 && nextPos < 100001 && nextCost < visited[nextPos]) {
                visited[nextPos] = nextCost;
                pq.add(new Node(nextPos, nextCost));
            }
        }

        return visited[K];
    }
}

class Node {
    int pos;
    int cost;

    public Node(int pos, int cost){
        this.pos = pos;
        this.cost = cost;
    }
}