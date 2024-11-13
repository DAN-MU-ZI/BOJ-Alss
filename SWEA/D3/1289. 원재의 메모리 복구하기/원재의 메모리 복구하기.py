T = int(input())
for test_case in range(1, T + 1):
    line = list(map(int, list(input())))

    idx = 0
    state = int(False)
    answer = 0
    while idx < len(line):
        if line[idx] != state:
            state = int(not state)
            answer += 1
        idx += 1
    print(f"#{test_case} {answer}")
