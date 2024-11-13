T = 10
for test_case in range(1, T + 1):
    N = int(input())
    arr = [list(input()) for _ in range(8)]

    answer = 0
    for i in range(8):
        for shift in range(8 - N + 1):
            char = arr[i][shift : shift + N]
            flag = False
            for j in range(N // 2):
                if char[j] != char[-j - 1]:
                    flag = True
                    break
            if not flag:
                answer += 1

            char = ""
            for k in range(N):
                char += arr[shift + k][i]
            flag = False
            for j in range(N // 2):
                if char[j] != char[-j - 1]:
                    flag = True
                    break
            if not flag:
                answer += 1
    print(f"#{test_case} {answer}")
