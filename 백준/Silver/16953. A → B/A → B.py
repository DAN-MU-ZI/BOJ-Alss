import sys
from collections import deque

input = sys.stdin.readline

A, B = map(int, input().split())
visited = set()  # 필요한 숫자만 저장

stk = deque()
stk.append((A, 0))  # 숫자와 연산 횟수를 함께 저장

while stk:
    num, cnt = stk.popleft()

    if num > B:  # B를 초과하면 더 이상 탐색하지 않음
        continue

    if num in visited:  # 이미 방문한 숫자는 스킵
        continue

    visited.add(num)  # 현재 숫자를 방문 처리

    if num == B:  # B에 도달하면 결과 출력 후 종료
        print(cnt + 1)
        sys.exit()

    # 다음 숫자 추가
    stk.append((num * 2, cnt + 1))  # 곱하기 2
    stk.append((int(str(num) + "1"), cnt + 1))  # 숫자 뒤에 1 추가

# B에 도달할 수 없을 경우
print(-1)
