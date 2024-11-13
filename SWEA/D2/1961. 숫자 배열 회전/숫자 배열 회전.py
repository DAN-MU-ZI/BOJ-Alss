T = int(input())

for test_case in range(1, T + 1):
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]
    print(f'#{test_case}')
    
    result = [[0] * 3 * (N) for _ in range(N)]
    
    col_shift = 0
    # rotate 90
    for i in range(N):
        line = arr[i]
        for j in range(N):
            result[j][N-1-i]=line[j]
    # rotate 180
    col_shift += N
    for i in range(N):
        line = arr[i]
        for j in range(N):
            result[N-1-i][col_shift+N-1-j]=line[j]
    # rotate 270
    col_shift += N
    for i in range(N):
        line = arr[i]
        for j in range(N):
            result[N-1-j][col_shift+i]=line[j]
            
    for line in result:
        buffer = []
        for i in range(3):
            sub = [str(x) for x in line[i*N:(i+1)*N]]
            buffer.append(''.join(sub))
        print(' '.join(buffer))