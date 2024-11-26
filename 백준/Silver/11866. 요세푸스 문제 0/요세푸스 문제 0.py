import sys

input = sys.stdin.readline

N, K = map(int, input().split())
arr = [str(i) for i in range(1, N + 1)]
results = []
idx = -1
while len(results) != N:
    count = K
    while count:
        idx = (idx + 1) % N
        if arr[idx]:
            count -= 1
    results.append(arr[idx])
    arr[idx] = None

print(f'<{", ".join(results)}>')
