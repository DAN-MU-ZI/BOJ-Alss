import sys

input = sys.stdin.readline

N = int(input())
dots = [list(map(int, input().split())) for _ in range(N)]

dots.sort(key=lambda x: (x[0], x[1]))

results = []
for x, y in dots:
    results.append(f"{x} {y}")
for line in results:
    print(line)
