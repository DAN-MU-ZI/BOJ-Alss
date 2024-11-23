import sys

input = sys.stdin.readline

N, M = map(int, input().split())

arr = [list(map(int, input().strip())) for _ in range(N)]

answer = 1

for i in range(N):
    block = i + 1
    flag = False
    for r in range(N - block):
        if flag:
            break
        for c in range(M - block):
            if (
                arr[r][c] == arr[r + block][c]
                and arr[r][c] == arr[r][c + block]
                and arr[r][c] == arr[r + block][c + block]
            ):
                flag = True
                answer = block + 1
                break

print(answer**2)
