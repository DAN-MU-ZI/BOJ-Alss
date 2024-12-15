import sys
input = sys.stdin.readline

T = int(input().strip())
for _ in range(T):
    M, N, x, y = map(int, input().split())
    
    answer = -1
    # k는 x부터 시작해서 M씩 증가
    # 최대 M*N까지만 확인
    limit = M * N
    k = x
    while k <= limit:
        # y 값 매칭 확인
        if (k - 1) % N + 1 == y:
            answer = k
            break
        k += M
    
    print(answer)
