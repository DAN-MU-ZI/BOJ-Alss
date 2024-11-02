directions = [
    [0, 1],
    [1, 0],
    [0, -1],
    [-1, 0],
]


def is_valid(r, c, N):
    return 0 <= r and r < N and 0 <= c and c < N


def get_next_pos(r, c, di):
    dr, dc = directions[di]
    return r + dr, c + dc


def rotate_direction(di):
    return (di + 1) % 4

T = int(input())
for test_case in range(1, T + 1):
    print(f"#{test_case}")
    N = int(input())
    arr = [[0] * N for _ in range(N)]

    pos = [0, 0]
    stk = 1
    di = 0

    while not arr[pos[0]][pos[1]]:
        r, c = pos
        arr[r][c] = stk
        stk += 1

        nr, nc = get_next_pos(r, c, di)
        if is_valid(nr, nc, N):
            if arr[nr][nc]:
                di = rotate_direction(di)
                nr, nc = get_next_pos(r, c, di)
        else:
            di = rotate_direction(di)
            nr, nc = get_next_pos(r, c, di)
        if not is_valid(nr, nc, N):
            break
        pos = [nr, nc]

    for line in arr:
        for i in line:
            print(i, end=" ")
        print()