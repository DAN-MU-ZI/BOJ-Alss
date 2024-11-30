import sys

input = sys.stdin.readline

A, B, V = map(int, input().split())

distance_per_day = A - B

if (V - A) % distance_per_day:
    print((V - A) // distance_per_day + 2)
else:
    print((V - A) // distance_per_day + 1)
