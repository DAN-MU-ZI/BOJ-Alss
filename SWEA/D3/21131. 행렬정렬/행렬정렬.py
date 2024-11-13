T = int(input())
for test_case in range(1, T + 1):
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]
	
    answer = 0
    line = arr[0]
    states = [False] * N
    for i in range(N):
        if line[i] != i + 1:
            states[i] = True
    
    for i in range(1, N):
        if states[i-1] == False and states[i] == True:
            answer+=2
    
    if N > 1:
        answer-=states[1]
    print(answer)