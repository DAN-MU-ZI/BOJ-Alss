import sys

input = sys.stdin.readline

N, M = map(int, input().split())
arr = list(map(int, input().split()))

answer = 0
for i in range(N):
    step_1 = arr[i]
    if step_1 > M - 6:
        continue
    for j in range(N):
        if i == j:
            continue
        step_2 = step_1 + arr[j]
        if step_2 > M - 3:
            continue
        for k in range(N):
            if i == k or j == k:
                continue
            step_3 = step_2 + arr[k]

            if step_3 <= M:
                answer = max(answer, step_3)
print(answer)
