import sys

input = sys.stdin.readline

N = int(input())

num = 1
for i in range(2, N + 1):
    num *= i

answer = 0

while num:
    mod = num % 10
    if mod:
        break
    answer += 1
    num = num // 10

print(answer)
