from collections import deque

# 입력
N, M, k = map(int, input().split())
grid = []
sharks = {}
smell = [[[0, 0] for _ in range(N)] for _ in range(N)]  # 냄새 배열

# 방향: 위(0), 아래(1), 왼쪽(2), 오른쪽(3)
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# 격자 초기화
for i in range(N):
    row = list(map(int, input().split()))
    for j in range(N):
        if row[j] > 0:
            sharks[row[j]] = (i, j)  # 상어 위치 저장
    grid.append(row)

# 각 상어의 방향
directions = list(map(lambda x: int(x) - 1, input().split()))

# 각 상어의 방향 우선순위 (4개 방향)
priorities = {}
for i in range(1, M + 1):
    priorities[i] = [list(map(lambda x: int(x) - 1, input().split())) for _ in range(4)]

# 초기 냄새 정보 설정
for num, (x, y) in sharks.items():
    smell[x][y] = [num, k]

# 시뮬레이션 시작
time = 0
while len(sharks) > 1 and time <= 1000:
    time += 1
    new_positions = {}  # 다음 위치를 저장할 딕셔너리
    
    # 1. 상어 이동
    for num in sorted(sharks.keys()):  # 번호 작은 상어부터 처리
        x, y = sharks[num]
        direction = directions[num - 1]
        found = False

        # 냄새 없는 칸 찾기
        for d in priorities[num][direction]:
            nx, ny = x + dx[d], y + dy[d]
            if 0 <= nx < N and 0 <= ny < N and smell[nx][ny][1] == 0:
                new_positions[num] = (nx, ny, d)
                found = True
                break

        # 만약 이동할 칸이 없으면, 자신의 냄새가 있는 칸으로 이동
        if not found:
            for d in priorities[num][direction]:
                nx, ny = x + dx[d], y + dy[d]
                if 0 <= nx < N and 0 <= ny < N and smell[nx][ny][0] == num:
                    new_positions[num] = (nx, ny, d)
                    break

    # 2. 상어 위치 정리 (충돌 해결)
    new_sharks = {}
    for num, (nx, ny, d) in new_positions.items():
        if (nx, ny) not in new_sharks or num < new_sharks[(nx, ny)][0]:
            new_sharks[(nx, ny)] = (num, d)

    # 3. 상어 목록 업데이트
    sharks.clear()
    for (nx, ny), (num, d) in new_sharks.items():
        sharks[num] = (nx, ny)
        directions[num - 1] = d

    # 4. 냄새 갱신
    for i in range(N):
        for j in range(N):
            if smell[i][j][1] > 0:
                smell[i][j][1] -= 1  # 기존 냄새 감소
                if smell[i][j][1] == 0:
                    smell[i][j][0] = 0  # 냄새가 완전히 사라짐

    # 5. 현재 상어 위치에 새로운 냄새 추가
    for num, (x, y) in sharks.items():
        smell[x][y] = [num, k]

# 결과 출력
print(time if time <= 1000 else -1)
