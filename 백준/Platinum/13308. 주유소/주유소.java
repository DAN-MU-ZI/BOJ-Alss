import java.io.*;

import java.util.*;

public class Main {

    // 상태 클래스: 현재 도시, 지금까지의 최소 주유 가격, 누적 비용

    static class State implements Comparable<State> {

        int city;

        int minPrice;

        long cost;

        public State(int city, int minPrice, long cost) {

            this.city = city;

            this.minPrice = minPrice;

            this.cost = cost;

        }

        @Override

        public int compareTo(State other) {

            return Long.compare(this.cost, other.cost);

        }

    }

    // 간선 클래스: 도착 도시와 도로의 길이

    static class Edge {

        int to, length;

        public Edge(int to, int length) {

            this.to = to;

            this.length = length;

        }

    }

    public static void main(String[] args) throws IOException {

        // 입력 처리

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(st.nextToken());

        int[] price = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {

            price[i] = Integer.parseInt(st.nextToken());

        }

        // 각 도시의 인접 리스트 구성 (양방향)

        List<Edge>[] graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {

            graph[i] = new ArrayList<>();

        }

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());

            int b = Integer.parseInt(st.nextToken());

            int l = Integer.parseInt(st.nextToken());

            graph[a].add(new Edge(b, l));

            graph[b].add(new Edge(a, l));

        }

        // dp[도시][현재까지의 최소 주유 가격] = 해당 상태까지 도달하는 최소 비용

        long[][] dp = new long[N + 1][2501];

        for (int i = 1; i <= N; i++) {

            Arrays.fill(dp[i], Long.MAX_VALUE - 1); // 오버플로우 방지

        }

        PriorityQueue<State> pq = new PriorityQueue<>();

        int startMinPrice = price[1];

        dp[1][startMinPrice] = 0;

        pq.offer(new State(1, startMinPrice, 0));

        // 다익스트라 알고리즘

        while (!pq.isEmpty()) {

            State cur = pq.poll();

            int curCity = cur.city;

            int curMin = cur.minPrice;

            long curCost = cur.cost;

            // 이미 더 낮은 비용으로 방문한 상태라면 넘어감

            if (curCost > dp[curCity][curMin]) continue;

            // 목표 도시에 도착한 경우 최소 비용 출력 후 종료

            if (curCity == N) {

                System.out.println(curCost);

                return;

            }

            // 인접 도시로 상태 전이

            for (Edge edge : graph[curCity]) {

                int nextCity = edge.to;

                int nextMin = Math.min(curMin, price[nextCity]);

                long newCost = curCost + (long) edge.length * curMin;

                if (newCost < dp[nextCity][nextMin]) {

                    dp[nextCity][nextMin] = newCost;

                    pq.offer(new State(nextCity, nextMin, newCost));

                }

            }

        }

        // 정답 출력 (최소 비용 찾기)

        long answer = Long.MAX_VALUE;

        for (int i = 1; i <= 2500; i++) {

            if (dp[N][i] < answer) {

                answer = dp[N][i];

            }

        }

        System.out.println(answer);

    }

}