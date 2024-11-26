import sys

input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().split()))


def is_prime(n):
    if n == 1:
        return False
    for i in range(2, n):
        if n % i == 0:
            return False
    return True


answer = 0
for num in arr:
    if is_prime(num):
        answer += 1
print(answer)
