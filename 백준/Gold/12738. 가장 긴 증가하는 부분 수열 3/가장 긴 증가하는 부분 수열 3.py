import sys

input = sys.stdin.readline

N = int(input())

A = list(map(int, input().split()))

arr = []

for i in range(N):
    node = A[i]

    left = 0
    right = len(arr) - 1
    pos = -1
    while left <= right:
        mid = (left + right) // 2

        if node > arr[mid]:
            left = mid + 1
        else:
            right = mid - 1
            pos = mid

    if pos == -1:
        arr.append(node)
    else:
        arr[pos] = node


print(len(arr))
