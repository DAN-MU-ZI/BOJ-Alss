T = int(input())
for test_case in range(1, T + 1):
    N , M = map(int, input().split())
    A = list(map(int, input().split()))
    B = list(map(int, input().split()))
    
    if len(A) > len(B):
        tmp = A
        A = B
        B = tmp
    
    best = -(2**63 - 1)
    for shift in range(len(B)-len(A)+1):
        tmp = 0
        for i in range(len(A)):
            tmp+=A[i]*B[shift+i]
        best = max(best, tmp)
    print(f'#{test_case} {best}')