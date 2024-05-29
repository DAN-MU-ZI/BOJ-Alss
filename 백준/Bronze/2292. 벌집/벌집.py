import sys

readline = sys.stdin.readline

N = int(readline())

cells = 1
depth = 1
while N > cells:
    cells += 6 * depth
    depth += 1
print(depth)
