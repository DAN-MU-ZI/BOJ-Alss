import sys

input = sys.stdin.readline


def solution():
    n = int(input())
    dp = [n] * (n + 1)
    dp[0] = 0
    dp[1] = 0

    for i in range(2, n + 1):
        dp[i] = dp[i - 1] + 1

        if i % 3 == 0 and dp[i] > (dp[i // 3] + 1):
            dp[i] = dp[i // 3] + 1
        if i % 2 == 0 and dp[i] > (dp[i // 2] + 1):
            dp[i] = dp[i // 2] + 1

    print(dp[n])

    s = dp[n]
    save = n
    answer = []
    while s >= 0:
        answer.append(save)
        if save % 3 == 0 and dp[save] == dp[save // 3] + 1:
            save = save // 3
            s -= 1
            continue
        if save % 2 == 0 and dp[save] == dp[save // 2] + 1:
            save = save // 2
            s -= 1
            continue

        save = save - 1
        s -= 1

    print(*answer)


solution()
