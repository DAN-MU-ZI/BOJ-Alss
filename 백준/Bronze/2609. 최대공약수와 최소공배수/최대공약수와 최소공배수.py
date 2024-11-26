import sys

input = sys.stdin.readline

A, B = map(int, input().split())

# 최대 공약수 구하기
div = 1
for i in range(2, min(A + 1, B + 1)):
    if A % i == 0 and B % i == 0:
        div = i
print(div)

# 최소 공배수 구하기
print(int(A * B // div))
