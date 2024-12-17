import sys
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(n)]


# find target => this is start point
def find_start(arr, n, m):
    for i in range(n):
        for j in range(m):
            if arr[i][j] == 2:
                return i, j


# init visited map
def init_visited(arr, n, m):
    visited = [[-1] * m for _ in range(n)]
    for i in range(n):
        for j in range(m):
            if arr[i][j] == 0:
                visited[i][j] = 0
    return visited


directions = ((1, 0), (-1, 0), (0, 1), (0, -1))


def is_valid(r, c, n, m):
    return 0 <= r and r < n and 0 <= c and c < m


def is_visited(r, c, visited):
    return visited[r][c] >= 0


r, c = find_start(arr, n, m)
visited = init_visited(arr, n, m)

stk = deque()
stk.append([r, c, 0])

while stk:
    r, c, cost = stk.popleft()

    if is_visited(r, c, visited):
        continue

    visited[r][c] = cost

    for dr, dc in directions:
        nr, nc = r + dr, c + dc

        if not is_valid(nr, nc, n, m):
            continue
        if is_visited(nr, nc, visited):
            continue
        stk.append([nr, nc, cost + 1])

for i in range(n):
    for j in range(m):
        print(visited[i][j], end=" ")
    print()
