for test_case in range(1, 10 + 1):
    answer = 0
    N = int(input())
    arr = list(map(int, input().split()))
    
    left = 2
    right = N-2
    for i in range(left, right):
        highest = max(arr[i-2], arr[i-1], arr[i+1], arr[i+2])
        if arr[i] > highest:
            answer += arr[i] - highest

    print(f'#{test_case} {answer}')