import sys

input = sys.stdin.readline


def dfs(target, row, col, right_up, left_down):
    global answer

    if row == target:
        answer += 1
        return

    for i in range(target):
        leftIdx = i + row
        rightIdx = i - row + target

        if col[i] or right_up[rightIdx] or left_down[leftIdx]:
            continue

        col[i] = True
        right_up[rightIdx] = True
        left_down[leftIdx] = True
        dfs(target, row + 1, col, right_up, left_down)
        col[i] = False
        right_up[rightIdx] = False
        left_down[leftIdx] = False


def solution():
    global answer
    n = int(input())
    answer = 0

    row = 0
    col = [False] * n
    right_up = [False] * (2 * n + 1)
    left_down = [False] * (2 * n + 1)

    dfs(n, row, col, right_up, left_down)
    print(answer)


solution()
