import sys

input = sys.stdin.readline

N, M = map(int, input().split())
T_list = list(map(int, input().split()))
party_list = [list(map(int, input().split())) for _ in range(M)]

parent = [i for i in range(N + 1)]
rank = [0] * (N + 1)


def find(x):
    if parent[x] != x:
        parent[x] = find(parent[x])
    return parent[x]


def union(x, y):
    rootX = find(x)
    rootY = find(y)

    if rootX == rootY:
        return

    if rank[rootX] < rank[rootY]:
        parent[rootX] = rootY
    elif rank[rootY] < rank[rootX]:
        parent[rootY] = rootX
    else:
        parent[rootY] = rootX
        rank[rootX] += 1


for t in T_list[1:]:
    union(0, t)

for _ in range(M):
    for party in party_list:
        flag = False
        for p in party[1:]:
            if find(p) == 0:
                flag = True
                break
        if flag:
            for p in party[1:]:
                union(0, p)

answer = 0
for party in party_list:
    flag = True
    for p in party[1:]:
        if find(p) == 0:
            flag = False
            break
    if flag:
        answer += 1
print(answer)
