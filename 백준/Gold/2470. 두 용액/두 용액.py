import sys

input = sys.stdin.readline


def solution():
    n = int(input())
    arr = list(map(int, input().split()))
    arr.sort()

    if n == 2:
        print(arr[0], arr[-1])
        return
    if arr[0] >= 0:
        print(arr[0], arr[1])
        return
    if arr[-1] <= 0:
        print(arr[-2], arr[-1])
        return

    s, e = 0, n - 1
    answer = abs(arr[s] + arr[e])
    ns, ne = s, e
    while s < e:
        cur = arr[s] + arr[e]
        if answer > abs(cur):
            ns, ne = s, e
            answer = abs(cur)

        if cur < 0:
            s += 1
        elif cur == 0:
            break
        else:
            e -= 1

    print(arr[ns], arr[ne])


solution()
