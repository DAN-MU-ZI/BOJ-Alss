import sys

input = sys.stdin.readline

nums = list(map(int, input().split()))

answer = 0
for num in nums:
    tmp = num**2 % 10
    answer = (answer + tmp) % 10
print(answer)
