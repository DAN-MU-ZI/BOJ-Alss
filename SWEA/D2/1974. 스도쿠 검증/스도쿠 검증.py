T = int(input())


def check_section(r, c):
    visited = [False] * 10
    for i in range(3):
        for j in range(3):
            cell = arr[r * 3 + i][c * 3 + j]
            if visited[cell]:
                return False
            visited[cell] = True
    return True


def check_row(r):
    visited = [False] * 10
    for i in range(9):
        cell = arr[r][i]
        if visited[cell]:
            return False
        visited[cell] = True
    return True


def check_col(c):
    visited = [False] * 10
    for i in range(9):
        cell = arr[i][c]
        if visited[cell]:
            return False
        visited[cell] = True
    return True


def check():
    for r in range(3):
        for c in range(3):
            if not check_section(r, c):
                return 0
    for i in range(9):
        if not check_row(i):
            return 0
        if not check_col(i):
            return 0
    return 1


for test_case in range(1, T + 1):
    arr = [list(map(int, input().split())) for _ in range(9)]

    print(f"#{test_case} {check()}")
