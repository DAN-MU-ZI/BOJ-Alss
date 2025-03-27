import sys


def solution():
    n = int(sys.stdin.readline())

    lines = []
    for i in range(n):
        a, b = map(int, sys.stdin.readline().split())
        lines.append([a, b])

    lines.sort(key=lambda x: x[0])

    dp = [1 for _ in range(len(lines) + 1)]

    for i in range(1, len(lines)):
        for j in range(0, i):
            if lines[j][1] < lines[i][1]:
                dp[i] = max(dp[i], dp[j] + 1)

    print(len(lines) - max(dp))


solution()
