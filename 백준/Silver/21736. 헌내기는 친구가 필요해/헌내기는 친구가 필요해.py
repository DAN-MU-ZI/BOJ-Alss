import sys
from collections import deque

input = sys.stdin.readline

N, M = map(int, input().split())
arr = [list(input().strip()) for _ in range(N)]
directions = ((1, 0), (-1, 0), (0, -1), (0, 1))


def getStart(arr):
    for r in range(N):
        for c in range(M):
            if arr[r][c] == "I":
                return r, c


x, y = getStart(arr)
stk = deque([[x, y]])
visited = [[False] * M for _ in range(N)]


def is_valid(r, c):
    return 0 <= r and r < N and 0 <= c and c < M


answer = 0
while stk:
    pos = stk.popleft()
    r, c = pos

    if visited[r][c]:
        continue

    visited[r][c] = True
    if arr[r][c] == "P":
        answer += 1

    for dr, dc in directions:
        nr, nc = r + dr, c + dc
        if is_valid(nr, nc):
            if arr[nr][nc] == "X":
                continue
            if visited[nr][nc]:
                continue
            stk.append([nr, nc])

if answer == 0:
    print("TT")
else:
    print(answer)
