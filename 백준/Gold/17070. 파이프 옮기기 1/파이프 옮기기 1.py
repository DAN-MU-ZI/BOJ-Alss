import sys
from collections import deque

input = sys.stdin.readline

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]

dp = {k: [[0] * (N + 1) for _ in range(N + 1)] for k in ["가로", "세로", "대각선"]}

if arr[0][2] == 0:
    dp["가로"][1][3] = 1
if arr[0][2] == 0 and arr[1][1] == 0 and arr[1][2] == 0:
    dp["대각선"][2][3] = 1

for r in range(1, N + 1):
    for c in range(1, N + 1):
        if dp["가로"][r][c] or dp["세로"][r][c] or dp["대각선"][r][c]:
            continue

        if arr[r - 1][c - 1] == 1:
            continue

        if c - 2 >= 0 and arr[r - 1][c - 2] == 0:
            dp["가로"][r][c] += dp["가로"][r][c - 1] + dp["대각선"][r][c - 1]

        if r - 2 >= 0 and arr[r - 2][c - 1] == 0:
            dp["세로"][r][c] += dp["세로"][r - 1][c] + dp["대각선"][r - 1][c]

        if (
            c - 2 >= 0
            and r - 2 >= 0
            and arr[r - 1][c - 2] == 0
            and arr[r - 2][c - 1] == 0
            and arr[r - 2][c - 2] == 0
        ):
            dp["대각선"][r][c] += (
                dp["대각선"][r - 1][c - 1]
                + dp["세로"][r - 1][c - 1]
                + dp["가로"][r - 1][c - 1]
            )


print(sum([dp["가로"][-1][-1], dp["세로"][-1][-1], dp["대각선"][-1][-1]]))
