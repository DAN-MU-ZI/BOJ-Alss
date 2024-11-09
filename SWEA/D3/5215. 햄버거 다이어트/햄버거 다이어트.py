def dfs(idx, score, stk):
    global answer
    answer = max(answer, score)

    for i in range(idx, N):
        if visited[i]:
            continue
        if stk + arr[i][1] > L:
            continue
        visited[i] = True
        dfs(i, score + arr[i][0], stk + arr[i][1])
        visited[i] = False


global answer
T = int(input())
for test_case in range(1, T + 1):
    answer = 0
    N, L = map(int, input().split())
    visited = [False] * N
    arr = [list(map(int, input().split())) for _ in range(N)]
    dfs(0, 0, 0)

    print(f"#{test_case} {answer}")
