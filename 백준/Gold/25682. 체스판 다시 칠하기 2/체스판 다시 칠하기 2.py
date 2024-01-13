import sys

input = sys.stdin.readline


def solution():
    n, m, k = map(int, input().split())
    board = [input().strip() for _ in range(n)]

    b = [[0] * (m + 1) for _ in range(n + 1)]
    w = [[0] * (m + 1) for _ in range(n + 1)]

    for i in range(n):
        for j in range(m):
            if (i + j) % 2 == 0:
                if board[i][j] == "B":
                    w[i + 1][j + 1] = 1
                else:
                    b[i + 1][j + 1] = 1
            else:
                if board[i][j] == "B":
                    b[i + 1][j + 1] = 1
                else:
                    w[i + 1][j + 1] = 1

            b[i + 1][j + 1] += b[i][j + 1] + b[i + 1][j] - b[i][j]
            w[i + 1][j + 1] += w[i][j + 1] + w[i + 1][j] - w[i][j]

    answer = 2000 * 2000
    for i in range(k, n + 1):
        for j in range(k, m + 1):
            b_tmp = b[i][j] - b[i][j - k] - b[i - k][j] + b[i - k][j - k]
            w_tmp = w[i][j] - w[i][j - k] - w[i - k][j] + w[i - k][j - k]
            answer = min(answer, b_tmp, w_tmp)

    # for line in b:
    #     print(line)
    # print()

    # for line in w:
    #     print(line)
    print(answer)


solution()
