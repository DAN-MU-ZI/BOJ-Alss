import sys

input = sys.stdin.readline

N = int(input())
sizes = map(int, input().split())
T, P = map(int, input().split())

answer_T = 0
for size in sizes:
    div, mod = size // T, size % T
    if mod:
        div += 1
    answer_T += div

answer_P_div = N // P
answer_P_mod = N % P

print(answer_T)
print(answer_P_div, answer_P_mod)
