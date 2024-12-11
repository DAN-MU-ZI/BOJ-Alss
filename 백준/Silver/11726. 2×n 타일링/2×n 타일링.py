import sys

input = sys.stdin.readline

n = int(input())

dp = [[0, 0] for _ in range(n + 1)]
dp[1][1] = 1

if n > 1:
    dp[2][1] = 1
    dp[2][0] = 1
if n > 2:
    dp[3][1] = 2
    dp[3][0] = 1

for i in range(3, n + 1):
    dp[i][1] = (dp[i - 1][0] + dp[i - 1][1]) % 10007
    dp[i][0] = (dp[i - 2][0] + dp[i - 2][1]) % 10007

print(sum(dp[n]) % 10007)
