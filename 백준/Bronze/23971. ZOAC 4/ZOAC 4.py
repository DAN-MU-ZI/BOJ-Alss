import sys
import math

readline = sys.stdin.readline


H, W, N, M = map(int, readline().split())

print(((W + M) // (M + 1)) * ((H + N) // (N + 1)))
