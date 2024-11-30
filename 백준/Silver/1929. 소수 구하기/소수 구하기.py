import sys

input = sys.stdin.readline

M, N = map(int, input().split())

arr = [True] * (N + 1)
arr[0] = False
arr[1] = False

for i in range(2, N - 1):
    num = i + i
    while num <= N:
        arr[num] = False
        num += i

for i in range(1, N + 1):
    if arr[i] and M <= i:
        print(i)
