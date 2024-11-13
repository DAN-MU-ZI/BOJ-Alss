T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    arr = [[1] * (x + 1) for x in range(N)]

    for i in range(2, N):
        for j in range(1, i):
            arr[i][j] = arr[i][j - 1] + arr[i - 1][j]

    print(f"#{test_case}")
    for line in arr:
        line = [str(x) for x in line]
        print(" ".join(line))
