import sys

input = sys.stdin.readline

N = int(input())
S = list(map(int, input().split()))


counter = {}
left = 0
answer = 1
for right in range(N):
    counter.update({S[right]: counter.get(S[right], 0) + 1})

    while len(counter) > 2:
        counter.update({S[left]: counter.get(S[left]) - 1})
        if counter[S[left]] == 0:
            counter.pop(S[left])
        left += 1

    answer = max(answer, right - left + 1)
print(answer)
