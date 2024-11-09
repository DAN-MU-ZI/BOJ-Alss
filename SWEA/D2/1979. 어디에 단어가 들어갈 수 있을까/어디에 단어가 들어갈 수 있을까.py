T = int(input())
for test_case in range(1, T + 1):
    N, K = map(int, input().split())
    arr = [input().split() for _ in range(N)]
    arr_t = list(zip(*arr))

    answer = 0
    for i in range(N):
        line = arr[i]
        slots = "".join(line).split("0")
        answer += sum([True for slot in slots if slot == "1" * K])

        line = arr_t[i]
        slots = "".join(line).split("0")
        answer += sum([True for slot in slots if slot == "1" * K])

    print(f"#{test_case} {answer}")
