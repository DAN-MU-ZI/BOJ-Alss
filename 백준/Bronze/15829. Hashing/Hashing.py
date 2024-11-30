import sys

input = sys.stdin.readline

L = int(input())
line = input().strip()


def solution(L, line):
    r = 31
    M = 1234567891

    H = 0
    for i in range(L):
        c = line[i]
        idx = ord(c) - ord("a") + 1
        mul = 1
        for _ in range(i):
            mul = (mul * r) % M
        H += (idx * mul) % M
    return H % M


answer = solution(L, line)
print(answer)
