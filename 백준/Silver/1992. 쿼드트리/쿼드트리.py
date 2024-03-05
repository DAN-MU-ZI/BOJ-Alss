import sys


input = sys.stdin.readline


def solution():
    n = int(input())
    arr = [input().strip() for _ in range(n)]
    answer = []

    def dfs(x, y, size):
        cur = arr[x][y]
        break_flag = False
        for i in range(x, x + size):
            for j in range(y, y + size):
                if arr[i][j] != cur:
                    break_flag = True
                    break
            if break_flag:
                break

        if break_flag:
            quad_size = size // 2
            answer.append("(")
            for i in range(x, x + size, quad_size):
                for j in range(y, y + size, quad_size):
                    dfs(i, j, quad_size)
            answer.append(")")
        else:
            answer.append(cur)

    dfs(0, 0, n)
    print("".join(answer))


solution()
