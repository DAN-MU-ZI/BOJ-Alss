import sys, math

input = sys.stdin.readline

n = int(input())

dp = [4] * (n + 1)

dp[0] = 0
for i in range(1, min(n + 1, 4)):
    dp[i] = i

for i in range(4, n + 1):
    for j in range(2, int(math.sqrt(i)) + 1):
        div = i // (j**2)
        prev = i - div * (j**2)
        dp[i] = min(dp[i], dp[prev] + div)
print(dp[n])
