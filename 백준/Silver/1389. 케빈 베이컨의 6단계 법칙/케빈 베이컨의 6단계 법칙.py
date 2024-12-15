import sys

input = sys.stdin.readline

N, M = map(int, input().split())
arr = [[N - 1] * (N) for _ in range(N)]

for _ in range(M):
    f, t = map(int, input().split())
    arr[f - 1][t - 1] = 1
    arr[t - 1][f - 1] = 1

for k in range(N):
    for i in range(N):
        for j in range(N):
            if i == j:
                continue
            arr[i][j] = min(arr[i][j], arr[i][k] + arr[k][j])

best_idx = 0
best_score = (N - 1) * N
for i in range(N):
    score = sum(arr[i]) - (N - 1)
    if score < best_score:
        best_idx = i
        best_score = score

print(best_idx + 1)
