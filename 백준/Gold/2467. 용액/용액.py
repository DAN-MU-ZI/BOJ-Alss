import sys

input = sys.stdin.readline

N = int(input())
M = list(map(int, input().split()))


def solution(N, M):
    start, end = 0, N - 1
    best = abs(M[start] + M[end])
    prev_start, prev_end = start, end
    while start < end:
        cur = M[start] + M[end]
        if best > abs(cur):
            best = abs(cur)
            prev_start, prev_end = start, end

        if cur < 0:
            start += 1
        elif cur == 0:
            break
        else:
            end -= 1

    print(M[prev_start], M[prev_end])


solution(N, M)
