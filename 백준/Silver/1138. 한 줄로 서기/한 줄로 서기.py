import sys

input = sys.stdin.readline

N = int(input())
arr = list(map(int, input().split()))
answer = [0] * N

# N 명까지 순회하기
for i in range(N):
    # i+1 번째 사람보다 큰 사람이 얼마나 있는지
    count = 0
    # answer를 전부 돌면서
    for j in range(N):
        # count와 일치하고 처음으로 만나는 빈공간에 들어가기
        if count == arr[i] and answer[j] == 0:
            answer[j] = str(i + 1)
            break
        # 빈공간은 자신보다 큰 사람들이 들어갈 곳
        if answer[j] == 0:
            count += 1


print(" ".join(answer))
