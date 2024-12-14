import sys

input = sys.stdin.readline

CEIL = 256
FLOOR = 0

N, M, B = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]


def is_floor(arr):
    for r in range(N):
        for c in range(M):
            if arr[r][c] != arr[0][0]:
                return False
    return True


top = 0
bottom = 256
for r in range(N):
    for c in range(M):
        top = max(top, arr[r][c])
        bottom = min(bottom, arr[r][c])

time = top * 2 * N * M
floor = 0
for height in range(bottom, top + 1):
    pop = 0
    push = 0
    for r in range(N):
        for c in range(M):
            if arr[r][c] > height:
                pop += arr[r][c] - height
            else:
                push += height - arr[r][c]
    remain = pop + B - push
    if remain >= 0:
        result = pop * 2 + push
        if time < result:
            break
        time = result
        floor = height
print(time, floor)
