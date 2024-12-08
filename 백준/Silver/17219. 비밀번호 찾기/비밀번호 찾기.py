import sys

input = sys.stdin.readline

N, M = map(int, input().split())
arr = {k: v for k, v in [input().strip().split() for _ in range(N)]}
targets = [input().strip() for _ in range(M)]

for target in targets:
    print(arr.get(target))
