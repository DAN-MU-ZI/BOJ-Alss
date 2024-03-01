import sys
import copy

input = sys.stdin.readline


def dfs(target, row, col, right_up, left_down):
    global answer

    if row == target:
        answer += 1
        return

    for j in range(target):
        if col[j] or right_up[j - row] or left_down[j + row]:
            continue

        col[j] = True
        right_up[j - row] = True
        left_down[j + row] = True
        dfs(target, row + 1, col, right_up, left_down)
        col[j] = False
        right_up[j - row] = False
        left_down[j + row] = False


def solution():
    global answer
    n = int(input())
    answer = 0

    row = 0
    col = [False for _ in range(n)]
    right_up = [False for _ in range(2 * n + 1)]
    left_down = [False for _ in range(2 * n + 1)]

    dfs(n, row, col, right_up, left_down)
    print(answer)


solution()
