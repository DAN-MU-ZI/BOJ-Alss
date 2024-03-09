import sys

input = sys.stdin.readline


def solution():
    line = input().strip()
    boom = list(input().strip())
    boom_len = len(boom)
    stk = []

    for c in line:
        stk.append(c)
        if c == boom[-1] and stk[-boom_len:] == boom:
            del stk[-boom_len:]

    if stk:
        print("".join(stk))
    else:
        print("FRULA")


solution()
