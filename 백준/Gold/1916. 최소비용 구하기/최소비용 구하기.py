import sys
from heapq import heappop, heappush

input = sys.stdin.readline

INF = sys.maxsize
N = int(input())
M = int(input())

graph = {i: [] for i in range(N + 1)}
for _ in range(M):
    f, t, w = map(int, input().split())
    graph[f].append((t, w))

s, e = map(int, input().split())


def dijkstra(s, e, graph, n):
    distance = [INF] * (N + 1)
    distance[s] = 0
    heap = []
    heappush(heap, (0, s))

    while heap:
        dis, node = heappop(heap)

        if dis > distance[node]:
            continue

        for next_node, weight in graph[node]:
            cost = dis + weight

            if cost < distance[next_node]:
                distance[next_node] = cost
                heappush(heap, (cost, next_node))

    return distance[e]


print(dijkstra(s, e, graph, N))
