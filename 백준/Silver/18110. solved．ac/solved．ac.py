import sys

input = sys.stdin.readline


def round(n):
    result = int(n)
    if n - result >= 0.5:
        return result + 1
    return result


def solution(n, opinions):
    if n == 0:
        return 0

    opinions.sort()

    cut = round(n * 0.15)
    opinions = opinions[cut : n - cut]

    total = sum(opinions)
    div = len(opinions)
    answer = round(total / div)
    return answer


n = int(input())
opinions = [int(input()) for _ in range(n)]

answer = solution(n, opinions)
print(answer)
