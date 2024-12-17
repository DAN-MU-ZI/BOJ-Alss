import sys
from collections import deque

input = sys.stdin.readline

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]

visited = [[0] * N for _ in range(N)]

stk = deque()
for i in range(N):
    for j in range(N):
        if arr[i][j]:
            stk.append([i, j])

while stk:
    r, c = stk.popleft()

    if visited[r][c]:
        continue

    visited[r][c] = 1
    arr[r][c] = 1

    for i in range(N):
        if arr[c][i] != 1:
            continue
        stk.append([r, i])

for line in arr:
    for a in line:
        print(a, end=" ")
    print()
