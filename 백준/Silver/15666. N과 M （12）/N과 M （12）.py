import sys

input = sys.stdin.readline


def backtrack(start, path):
    # 수열의 길이가 M이면 출력
    if len(path) == M:
        print(*path)
        return

    # 비내림차순을 유지하며 탐색
    for i in range(start, len(numbers)):
        backtrack(i, path + [numbers[i]])


# 입력 처리
N, M = map(int, input().split())
numbers = list(map(int, input().split()))

# 중복 제거 및 정렬
numbers = sorted(set(numbers))

# 백트래킹 호출
backtrack(0, [])
