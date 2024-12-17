import sys

input = sys.stdin.readline

N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]


def is_valid_position(position):
    r, c = position
    return 0 <= r < N and 0 <= c < M


def is_valid(positions):
    for r, c in positions:
        if not is_valid_position([r, c]):
            return False
    return True


def calculate_sum(positions):
    return sum(arr[r][c] for r, c in positions)


# 모든 테트로미노 모양
tetrominoes = [
    # ㅡ 모양
    [(0, 0), (0, 1), (0, 2), (0, 3)],
    [(0, 0), (1, 0), (2, 0), (3, 0)],
    # ㅁ 모양
    [(0, 0), (0, 1), (1, 0), (1, 1)],
    # L 모양
    [(0, 0), (1, 0), (2, 0), (0, 1)],
    [(0, 0), (1, 0), (2, 0), (2, 1)],
    [(0, 1), (1, 1), (2, 1), (0, 0)],
    [(0, 1), (1, 1), (2, 1), (2, 0)],
    [(0, 0), (0, 1), (0, 2), (1, 0)],
    [(0, 0), (0, 1), (0, 2), (1, 2)],
    [(1, 0), (1, 1), (1, 2), (0, 0)],
    [(1, 0), (1, 1), (1, 2), (0, 2)],
    # Z 모양
    [(0, 0), (0, 1), (1, 1), (1, 2)],
    [(1, 0), (0, 1), (1, 1), (0, 2)],
    [(0, 0), (1, 0), (1, 1), (2, 1)],
    [(0, 1), (1, 0), (1, 1), (2, 0)],
    # T 모양
    [(0, 0), (0, 1), (0, 2), (1, 1)],
    [(1, 0), (1, 1), (1, 2), (0, 1)],
    [(0, 0), (1, 0), (2, 0), (1, 1)],
    [(0, 1), (1, 1), (2, 1), (1, 0)],
]

answer = 0
for r in range(N):
    for c in range(M):
        for shape in tetrominoes:
            positions = [(r + dr, c + dc) for dr, dc in shape]
            if is_valid(positions):
                answer = max(answer, calculate_sum(positions))

print(answer)
