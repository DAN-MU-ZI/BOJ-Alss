import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static int[][] inputs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        M = Integer.parseInt(br.readLine());
        inputs = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int[] input = new int[3];
            input[0] = Integer.parseInt(st.nextToken());
            input[1] = Integer.parseInt(st.nextToken());
            input[2] = Integer.parseInt(st.nextToken());
            inputs[i] = input;
        }

        int answer = solve();

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    static int solve() {
        // 현재 state에서 방문하지 않은 edge를 사용해서 다익스트라
        // 매 방문시 state를 visited로 기록

        // 현재 state 만들기

        HashMap<Integer, Integer> costs = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int hash = getHash(arr);
        costs.put(hash, 0);
        pq.add(new Node(hash, 0));

        Arrays.sort(arr);
        int target = getHash(arr);

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (costs.containsKey(node.hash) && node.cost > costs.get(node.hash)) {
                continue;
            }

            if (node.hash == target) {
                return node.cost;
            }

            for (int i = 0; i < M; i++) {
                int nextHash = apply(inputs[i][0], inputs[i][1], node.hash);
                int nextCost = inputs[i][2] + node.cost;

                if (costs.containsKey(nextHash)) {
                    if (nextCost < costs.get(nextHash)) {
                        pq.add(new Node(nextHash, nextCost));
                        costs.put(nextHash, nextCost);
                    }
                } else {
                    pq.add(new Node(nextHash, nextCost));
                    costs.put(nextHash, nextCost);
                }
            }
        }

        return -1;
    }

    static int getHash(int[] arr) {
        int hash = 0;
        for (int i = 0; i < N + 1; i++) {
            hash *= 10;
            hash += arr[i];
        }
        return hash;
    }

    static int apply(int a, int b, int hash) {
        int[] arr = new int[N + 1];
        for (int i = N; i > 0; i--) {
            arr[i] = hash % 10;
            hash /= 10;
        }

        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
        return getHash(arr);
    }

    static class Node implements Comparable<Node> {
        int hash, cost;

        public Node(int _hash, int _cost) {
            hash = _hash;
            cost = _cost;
        }

        public int compareTo(Node n) {
            return cost - n.cost;
        }
    }
}