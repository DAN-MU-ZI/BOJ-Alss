import sys

input = sys.stdin.readline

N = int(input())

dp = [-1] * (N + 1)
dp[3] = 1
if N >= 5:
    dp[5] = 1

for i in range(6, N + 1):
    if dp[i - 3] != -1 and dp[i - 5] != -1:
        dp[i] = 1 + min(dp[i - 3], dp[i - 5])
    elif dp[i - 3] != -1 and dp[i - 5] == -1:
        dp[i] = 1 + dp[i - 3]
    elif dp[i - 3] == -1 and dp[i - 5] != -1:
        dp[i] = 1 + dp[i - 5]

print(dp[N])
