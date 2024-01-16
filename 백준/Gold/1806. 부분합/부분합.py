import sys

input = sys.stdin.readline


def solution():
    n, s = map(int, input().split())
    arr = list(map(int, input().split())) + [0]

    start, end = 0, 0
    total = 0
    INF = 1e9
    answer = INF

    while start <= n and end <= n:
        if total < s:
            total += arr[end]
            end += 1
        elif total >= s:
            if answer > (end - start):
                answer = end - start

            total -= arr[start]
            start += 1

    if answer == INF:
        print(0)
    else:
        print(answer)


solution()
