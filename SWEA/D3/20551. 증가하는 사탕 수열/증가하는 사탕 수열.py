def solution(A, B, C):
    global answer

    tmp = 0
    if B >= C:
        target = C - 1
        minus = B - target
        B = target
        tmp += minus

    if A >= B:
        target = B - 1
        minus = A - target
        A = target
        tmp += minus

    if A <= 0 or B <= 0 or C <= 0:
        return

    answer = tmp


T = int(input())
for test_case in range(1, T + 1):
    global answer
    A, B, C = map(int, input().split())
    answer = -1
    solution(A, B, C)
    print(f"#{test_case} {answer}")
