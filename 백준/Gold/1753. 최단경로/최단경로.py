import sys
import heapq

def solve():
    input = sys.stdin.readline

    V, E = map(int, input().split())
    K = int(input())

    graph = [[] for _ in range(V + 1)]
    for _ in range(E):
        u, v, w = map(int, input().split())
        graph[u].append((v, w))

    INF = 10**18
    dist = [INF] * (V + 1)
    dist[K] = 0

    pq = [(0, K)]

    while pq:
        cur_d, u = heapq.heappop(pq)

        if cur_d > dist[u]:
            continue

        for v, w in graph[u]:
            nd = cur_d + w
            if nd < dist[v]:
                dist[v] = nd
                heapq.heappush(pq, (nd, v))

    out = ["INF" if dist[i] == INF else str(dist[i]) for i in range(1, V + 1)]
    sys.stdout.write("\n".join(out) + "\n")

if __name__ == "__main__":
    solve()