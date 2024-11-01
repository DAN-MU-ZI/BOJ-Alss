import sys

input = sys.stdin.readline

X, Y = map(int, input().split())

Z = int(Y * 100 // X)

left = 0
right = sys.maxsize * 2 + 1

answer = -1
while left <= right:
    mid = (left + right) // 2

    win_rate = int((Y + mid) * 100 // (X + mid))

    if win_rate > Z:
        answer = mid

    if win_rate > Z:
        right = mid - 1
    else:
        left = mid + 1

print(answer)
