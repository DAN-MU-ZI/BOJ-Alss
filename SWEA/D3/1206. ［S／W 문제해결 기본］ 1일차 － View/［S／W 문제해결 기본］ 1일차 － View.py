for test_case in range(1, 10 + 1):
    answer = 0
    N = int(input())
    arr = list(map(int, input().split()))
    
    left = 2
    right = N-2
    for i in range(left, right):
        best = arr[i]
        
        best = min(best, arr[i]-arr[i-1])
        best = min(best, arr[i]-arr[i-2])
        best = min(best, arr[i]-arr[i+1])
        best = min(best, arr[i]-arr[i+2])
        
        if best > 0: answer+=best

    print(f'#{test_case} {answer}')