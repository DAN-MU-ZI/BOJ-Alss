import sys

input = sys.stdin.readline

N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]

home_list = []
chicken_list = []
for r in range(N):
    for c in range(N):
        if arr[r][c] == 1:
            home_list.append([r, c])
        elif arr[r][c] == 2:
            chicken_list.append([r, c])

distance_map = [[0] * len(chicken_list) for _ in range(len(home_list))]

for r, home in enumerate(home_list):
    for c, chicken in enumerate(chicken_list):
        hr, hc = home
        cr, cc = chicken
        distance_map[r][c] = abs(hr - cr) + abs(hc - cc)

global ansewr
answer = sys.maxsize


def dfs(distance_map, visited, cnt, cur):
    global answer
    if cur == len(distance_map[0]):
        if cnt == M:
            total_distance = 0
            for i in range(len(distance_map)):
                h_dist = 100
                for j in range(len(distance_map[0])):
                    if visited[j]:
                        h_dist = min(h_dist, distance_map[i][j])
                total_distance += h_dist
            answer = min(answer, total_distance)

        return

    visited[cur] = True
    dfs(distance_map, visited, cnt + 1, cur + 1)

    visited[cur] = False
    dfs(distance_map, visited, cnt, cur + 1)


dfs(distance_map, [False] * len(chicken_list), 0, 0)
print(answer)
