import sys

input = sys.stdin.readline

N = int(input())
M = list(map(int, input().split()))


def solution(N, M):
    M.sort()
    stk = 0
    for i in range(N):
        result = func(i, 0, N - 1, M)
        if result is True:
            stk += 1

    print(stk)


def func(target, left, right, M):
    if left is target:
        left += 1
    if right is target:
        right -= 1
    while left < right:
        total = M[left] + M[right]
        if total == M[target]:
            return True

        if M[target] > total:
            left += 1
            if left is target:
                left += 1
        else:
            right -= 1
            if right is target:
                right -= 1

    return False


solution(N, M)
