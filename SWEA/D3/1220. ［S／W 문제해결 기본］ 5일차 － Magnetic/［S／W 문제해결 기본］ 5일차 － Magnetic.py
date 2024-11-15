T = 10
for test_case in range(1, T + 1):
    _ = int(input())
    arr = [list(map(int, input().split())) for _ in range(100)]
    arr = list(zip(*arr))

    answer = 0
    for line in arr:
        cur = 0
        for state in line:
            if state == 1:
                cur = state
            if cur == 1 and state == 2:
                answer += 1
                cur = 0

    print(f"#{test_case} {answer}")
