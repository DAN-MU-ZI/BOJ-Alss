T = int(input())
for test_case in range(1, T + 1):
    num = input()
    arr = list(map(int, input().split()))
    
    counter = [0] * 101
    
    for i in arr:
        counter[i]+=1
        
    highest_idx = 0
    for i in range(1, 101):
        if counter[highest_idx] <= counter[i]:
            highest_idx = i
	
    print(f'#{test_case} {highest_idx}')