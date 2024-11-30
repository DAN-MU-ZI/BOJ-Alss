import sys

input = sys.stdin.readline

N = int(input())
information = [list(map(int, input().split())) for _ in range(N)]


def solution(N, information):
    rank = [1] * N
    for i in range(N):
        weight, height = information[i]
        for j in range(N):
            if j == i:
                continue
            comp_w, comp_h = information[j]
            if weight > comp_w and height > comp_h:
                rank[j] += 1

    return " ".join([str(x) for x in rank])


answer = solution(N, information)
print(answer)
