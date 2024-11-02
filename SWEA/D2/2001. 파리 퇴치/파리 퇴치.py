T = int(input())
for test_case in range(1, 1 + T):
    N, M = map(int, input().split())

    arr = [list(map(int, input().split())) for _ in range(N)]
    acc = [[0] * N for _ in range(N)]

    for i in range(N):
        for j in range(N):
            for k in range(M):
                for l in range(M):
                    r = i + k
                    c = j + l
                    if 0 <= r and r < N and 0 <= c and c < N:
                        acc[r][c] += arr[i][j]

    best = 0
    for i in range(M - 1, N):
        for j in range(M - 1, N):
            best = max(best, acc[i][j])
    print(f"#{test_case} {best}")
