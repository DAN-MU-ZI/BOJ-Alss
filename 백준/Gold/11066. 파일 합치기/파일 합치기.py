import sys


def solution():
    n = int(sys.stdin.readline())
    arr = list(map(int, sys.stdin.readline().split()))

    s = [0 for _ in range(n + 1)]
    for i in range(1, n + 1):
        s[i] = s[i - 1] + arr[i - 1]

    dp = [[0 for _ in range(n + 1)] for _ in range(n + 1)]
    for i in range(1, n):
        dp[i][i + 1] = arr[i - 1] + arr[i]

    for i in range(n - 2, 0, -1):
        for j in range(i + 2, n + 1):
            dp[i][j] = (
                min([dp[i][k] + dp[k + 1][j] for k in range(i, j)]) + s[j] - s[i - 1]
            )
    print(dp[1][n])


for i in range(int(sys.stdin.readline())):
    solution()
