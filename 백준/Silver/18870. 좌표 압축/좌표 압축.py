import sys

input = sys.stdin.readline


def solution():
    n = int(input())
    xs = list(map(int, input().split()))

    xs_dict = {x: idx for idx, x in enumerate(sorted(list(set(xs))))}
    print(" ".join([str(xs_dict[x]) for x in xs]))


solution()
