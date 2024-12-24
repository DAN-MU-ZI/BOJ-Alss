import sys

input = sys.stdin.readline

N = int(input())
arr = [list(map(int, input().split())) for _ in range(N)]


def get_score_bitmask(mask):
    score = 0
    for x in range(N):
        if mask & (1 << x):
            for y in range(x + 1, N):
                if mask & (1 << y):
                    score += arr[x][y] + arr[y][x]
    return score


mask_full = (1 << N) - 1
answer = sys.maxsize

for subset in range(1, 1 << (N - 1)):
    complement = mask_full ^ subset

    s1 = get_score_bitmask(subset)
    s2 = get_score_bitmask(complement)
    answer = min(answer, abs(s1 - s2))

print(answer)
