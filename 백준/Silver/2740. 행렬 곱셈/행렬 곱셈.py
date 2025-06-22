import sys

input = sys.stdin.readline


def solution():
    n, m = map(int, input().split())
    op1 = [list(map(int, input().split())) for _ in range(n)]
    m, k = map(int, input().split())
    op2 = [list(map(int, input().split())) for _ in range(m)]

    for i in range(n):
        e = []
        for j in range(k):
            res = 0
            for l in range(m):
                res += op1[i][l] * op2[l][j]
            e.append(str(res))
        print(" ".join(e))


solution()
