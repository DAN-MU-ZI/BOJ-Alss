import sys
from collections import deque

input = sys.stdin.readline

N = int(input())
arr = [list(input().strip()) for _ in range(N)]

visited = [[False] * N for _ in range(N)]
visited_c = [[False] * N for _ in range(N)]

directions = ((1, 0), (-1, 0), (0, 1), (0, -1))


def is_valid_range(r, c):
    return 0 <= r and r < N and 0 <= c and c < N


def explore(arr, visited, r, c, ignore_color: bool = False):
    stk = deque()
    stk.append([r, c])

    while stk:
        r, c = stk.popleft()

        if visited[r][c]:
            continue

        visited[r][c] = True

        for dr, dc in directions:
            nr, nc = r + dr, c + dc

            if not is_valid_range(nr, nc):
                continue

            if visited[nr][nc]:
                continue

            if arr[r][c] != arr[nr][nc]:
                if ignore_color and (
                    (arr[r][c] == "R" or arr[r][c] == "G")
                    and (arr[nr][nc] == "R" or arr[nr][nc] == "G")
                ):
                    pass
                else:
                    continue
            stk.append([nr, nc])


norm_cnt = 0
abnorm_cnt = 0
for i in range(N):
    for j in range(N):
        if not visited[i][j]:
            explore(arr, visited, i, j, False)
            norm_cnt += 1
        if not visited_c[i][j]:
            explore(arr, visited_c, i, j, True)
            abnorm_cnt += 1
print(norm_cnt, abnorm_cnt)
