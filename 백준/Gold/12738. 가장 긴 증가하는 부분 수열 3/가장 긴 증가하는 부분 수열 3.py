import sys

input = sys.stdin.readline

N = int(input())

A = list(map(int, input().split()))

arr = []

for i in range(N):
    node = A[i]

    left = 0
    right = len(arr)
    while left < right:
        mid = (left + right) // 2
        if node > arr[mid]:
            left = mid + 1
        else:
            right = mid

    if len(arr) == right:
        arr.append(node)
    else:
        arr[right] = node


print(len(arr))
