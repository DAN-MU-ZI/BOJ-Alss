import sys

input = sys.stdin.readline

N = int(input())
M = int(input())
S = input().strip()

slide_len = N * 2 + 1
answer = 0

e = "I"
stk = 0
for c in S:
    if c != e:
        stk = 0
        e = "I"

    if c == e:
        stk += 1
        if stk >= slide_len and e == "I":
            answer += 1
        if e == "I":
            e = "O"
        else:
            e = "I"


print(answer)
