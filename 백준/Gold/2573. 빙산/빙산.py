import sys
from collections import deque

readline = sys.stdin.readline

n, m = map(int, readline().split())
arr = [list(map(int, readline().split())) for _ in range(n)]
directions = ((1, 0), (-1, 0), (0, 1), (0, -1))


def bfs(visited, x, y):
    queue = deque([(x, y)])
    visited[x][y] = True

    discount_list = []

    while queue:
        a, b = queue.popleft()
        sea_count = 0

        for dx, dy in directions:
            nx = a + dx
            ny = b + dy
            if 0 <= nx < n and 0 <= ny < m:
                if not arr[nx][ny]:
                    sea_count += 1
                elif not visited[nx][ny]:
                    queue.append((nx, ny))
                    visited[nx][ny] = True

        if sea_count:
            discount_list.append((a, b, sea_count))

    while discount_list:
        a, b, count = discount_list.pop()
        arr[a][b] = max(arr[a][b] - count, 0)


answer = 0
while True:
    visited = [[False] * (m + 1) for _ in range(n + 1)]

    is_break = False
    flag = False
    for i in range(n):
        if is_break:
            break

        for j in range(m):
            if not visited[i][j] and arr[i][j] != 0:
                if flag:
                    is_break = True
                    break

                bfs(visited, i, j)
                flag = True

    if flag == 0:
        answer = 0
        break
    if is_break:
        break

    answer += 1

print(answer)
