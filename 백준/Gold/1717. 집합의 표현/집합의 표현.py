from sys import stdin, setrecursionlimit

setrecursionlimit(10**9)

input = stdin.readline


def solution():
    n, m = map(int, input().split())
    arr = [x for x in range(n + 1)]

    def find_root(x):
        if x != arr[x]:
            arr[x] = find_root(arr[x])
        return arr[x]

    def union(a, b):
        ra = find_root(a)
        rb = find_root(b)

        if ra < rb:
            arr[rb] = ra
        else:
            arr[ra] = rb

    for _ in range(m):
        command, a, b = map(int, input().split())
        if command == 0:
            union(a, b)
        elif command == 1:
            if find_root(a) == find_root(b):
                print("YES")
            else:
                print("NO")


solution()
