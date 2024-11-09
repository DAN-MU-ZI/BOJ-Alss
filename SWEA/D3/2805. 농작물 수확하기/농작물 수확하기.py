T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    arr = [list(map(int, list(input()))) for _ in range(N)]

    answer = 0
    mid = N // 2
    answer += sum(arr[mid])

    for i in range(1, mid + 1):
        answer += sum(arr[mid + i][i : N - (i)])
        answer += sum(arr[mid - i][i : N - (i)])
    print(f"#{test_case} {answer}")
