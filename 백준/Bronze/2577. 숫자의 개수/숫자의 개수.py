import sys

input = sys.stdin.readline

A = int(input())
B = int(input())
C = int(input())

mul = A * B * C

counter = [0] * 10
while mul:
    num = mul % 10
    counter[num] += 1
    mul = mul // 10

for num in counter:
    print(num)
