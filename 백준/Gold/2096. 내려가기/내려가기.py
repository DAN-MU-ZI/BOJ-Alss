import sys

input = sys.stdin.readline

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]

min_prev_0 = arr[0][0]
min_prev_1 = arr[0][1]
min_prev_2 = arr[0][2]

max_prev_0 = arr[0][0]
max_prev_1 = arr[0][1]
max_prev_2 = arr[0][2]

for i in range(2, N + 1):
    min_next_0 = arr[i - 1][0] + min(min_prev_0, min_prev_1)
    min_next_1 = arr[i - 1][1] + min(min_prev_0, min_prev_1, min_prev_2)
    min_next_2 = arr[i - 1][2] + min(min_prev_1, min_prev_2)

    max_next_0 = arr[i - 1][0] + max(max_prev_0, max_prev_1)
    max_next_1 = arr[i - 1][1] + max(max_prev_0, max_prev_1, max_prev_2)
    max_next_2 = arr[i - 1][2] + max(max_prev_1, max_prev_2)

    min_prev_0, min_prev_1, min_prev_2 = min_next_0, min_next_1, min_next_2
    max_prev_0, max_prev_1, max_prev_2 = max_next_0, max_next_1, max_next_2

print(max(max_prev_0, max_prev_1, max_prev_2), min(min_prev_0, min_prev_1, min_prev_2))
