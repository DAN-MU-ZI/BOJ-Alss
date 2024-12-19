import sys

input = sys.stdin.readline

N, M = map(int, input().split())
arr = list(map(int, input().split()))
arr.sort()

stk = []
visited = [False] * N


def dfs():
    if len(stk) == M:
        print(" ".join(stk))

    prev = None
    for i in range(N):
        if visited[i]:
            continue
        if arr[i] == prev:
            continue

        visited[i] = True
        stk.append(str(arr[i]))
        prev = arr[i]
        dfs()

        visited[i] = False
        stk.pop()


dfs()
