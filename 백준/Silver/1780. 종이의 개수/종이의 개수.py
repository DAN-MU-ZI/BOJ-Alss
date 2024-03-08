import sys

input = sys.stdin.readline


def solution():
    n = int(input())
    arr = [list(input().split()) for _ in range(n)]
    count = {"-1": 0, "0": 0, "1": 0}

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
            cell_size = size // 3

            if cell_size == 1:
                for k in range(x, x + size, cell_size):
                    for l in range(y, y + size, cell_size):
                        count[arr[k][l]] += 1
                return

            for k in range(x, x + size, cell_size):
                for l in range(y, y + size, cell_size):
                    dfs(k, l, cell_size)
        else:
            count[cur] += 1

    dfs(0, 0, n)
    for v in count.values():
        print(v)


solution()
