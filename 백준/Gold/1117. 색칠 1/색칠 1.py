import sys

input = sys.stdin.readline

W, H, f, c, x1, y1, x2, y2 = map(int, input().split())

answer = W * H

if W - f < f:
    remain = W - f
else:
    remain = f

if x2 < remain:
    line = (x2 - x1) * 2
elif remain <= x1:
    line = x2 - x1
else:
    line = (remain - x1) * 2
    line += x2 - remain

block = line * (y2 - y1)
answer -= block * (c + 1)
print(answer)
