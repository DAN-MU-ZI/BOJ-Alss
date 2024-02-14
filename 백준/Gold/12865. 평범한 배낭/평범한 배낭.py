import sys


def func():
    n, k = map(int, sys.stdin.readline().split())
    dp = [[0 for _ in range(k + 1)] for _ in range(n + 1)]

    arr = []
    for _ in range(n):
        w, v = map(int, sys.stdin.readline().split())
        arr.append((w, v))

    for current_count, (w, v) in enumerate(arr, 1):
        for current_weight in range(1, k + 1):
            if current_weight < w:
                dp[current_count][current_weight] = dp[current_count - 1][
                    current_weight
                ]
            else:
                dp[current_count][current_weight] = max(
                    dp[current_count - 1][current_weight],
                    dp[current_count - 1][current_weight - w] + v,
                )

    print(dp[n][k])


func()
