def dfs(row):
    global answer
    if row == N:
        answer += 1
        return

    for c in range(N):
        if col[c] or right_up[row + c] or left_down[row - c]:
            continue

        col[c] = True
        right_up[row + c] = True
        left_down[row - c] = True
        dfs(row + 1)

        col[c] = False
        right_up[row + c] = False
        left_down[row - c] = False


global answer

T = int(input())
for test_case in range(1, T + 1):
    answer = 0
    N = int(input())

    col = [False] * N
    right_up = [False] * (2 * N - 1)
    left_down = [False] * (2 * N - 1)

    dfs(0)
    print(f"#{test_case} {answer}")
