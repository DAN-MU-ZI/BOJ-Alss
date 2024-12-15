import sys

input = sys.stdin.readline

N = int(input())
M = int(input())
S = input().strip()

slide = "IO" * N + "I"
answer = 0
for i in range(0, M - len(slide) + 1):
    if S[i : i + len(slide)] == slide:
        answer += 1
print(answer)
