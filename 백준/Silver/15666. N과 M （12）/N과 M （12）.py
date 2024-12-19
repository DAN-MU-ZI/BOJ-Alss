import sys

input = sys.stdin.readline

N, M = map(int, input().split())
arr = sorted(set(list(map(int, input().split()))))


def dfs(start, stk):
    if len(stk) == M:
        print(*stk)
        return

    for i in range(start, len(arr)):
        stk.append(arr[i])
        dfs(i, stk)
        stk.pop()


dfs(0, [])
