for test_case in range(1, 10 + 1):
    N = int(input())
    arr = list(map(int, input().split()))
    
    while N:
        arr.sort()
        N-=1
        arr[0]+=1
        arr[-1]-=1
    
    arr.sort()
    print(f'#{test_case} {arr[-1]-arr[0]}')