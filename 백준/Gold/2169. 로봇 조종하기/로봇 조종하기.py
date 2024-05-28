import sys
from collections import deque

readline = sys.stdin.readline


n, m = map(int, readline().split())
arr = [list(map(int, readline().split())) for _ in range(n)]

for i in range(1, m):
    arr[0][i] += arr[0][i - 1]

for i in range(1, n):
    cache = [[-1000000] * m for _ in range(2)]

    # left -> right
    for k in range(m):
        if k == 0:
            cache[0][k] = arr[i - 1][k] + arr[i][k]
            continue

        cache[0][k] = max(arr[i - 1][k] + arr[i][k], cache[0][k - 1] + arr[i][k])

    # right -> left
    for k in range(m - 1, -1, -1):
        if k == m - 1:
            cache[1][k] = arr[i - 1][k] + arr[i][k]
            continue

        cache[1][k] = max(arr[i - 1][k] + arr[i][k], cache[1][k + 1] + arr[i][k])

    for l in range(m):
        arr[i][l] = max(cache[0][l], cache[1][l])

print(arr[n - 1][m - 1])
