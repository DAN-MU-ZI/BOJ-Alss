day_by_month = {
    1:31,
    2:28,
    3:31,
    4:30,
    5:31,
    6:30,
    7:31,
    8:31,
    9:30,
    10:31,
    11:30,
    12:31
}

def solution(date):
    year = date[:4]
    month = date[4:6]
    day = date[6:8]
    
    if 1 > int(month) or int(month) > 12:
        return -1
    if 1 > int(day) or int(day) > day_by_month[int(month)]:
        return -1
    
    return f'{year}/{month}/{day}'
T = int(input())
for test_case in range(1, T + 1):
    date = input()
     
    print(f'#{test_case} {solution(date)}')
