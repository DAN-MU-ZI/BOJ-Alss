import sys

input = sys.stdin.readline

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]

dp = {
    "가로": [[0] * N for _ in range(N)],
    "세로": [[0] * N for _ in range(N)],
    "대각선": [[0] * N for _ in range(N)],
}

if arr[0][1] == 0:
    dp["가로"][0][1] = 1

for r in range(N):
    for c in range(N):
        if arr[r][c] == 1:
            continue

        if c > 0:
            dp["가로"][r][c] += dp["가로"][r][c - 1]
            dp["가로"][r][c] += dp["대각선"][r][c - 1]

        if r > 0:
            dp["세로"][r][c] += dp["세로"][r - 1][c]
            dp["세로"][r][c] += dp["대각선"][r - 1][c]

        if r > 0 and c > 0:
            if arr[r - 1][c] == 0 and arr[r][c - 1] == 0:
                dp["대각선"][r][c] += dp["가로"][r - 1][c - 1]
                dp["대각선"][r][c] += dp["세로"][r - 1][c - 1]
                dp["대각선"][r][c] += dp["대각선"][r - 1][c - 1]

print(dp["가로"][N - 1][N - 1] + dp["세로"][N - 1][N - 1] + dp["대각선"][N - 1][N - 1])
