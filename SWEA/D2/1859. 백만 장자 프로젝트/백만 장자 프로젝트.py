T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    N = int(input())
    costs = list(map(int, input().split()))
    
    max_price = 0
    profit = 0
    
    for cost in reversed(costs):
        if cost > max_price:
            max_price = cost
        else:
            profit += max_price - cost
    
    print(f'#{test_case} {profit}')
