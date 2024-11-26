import sys
from collections import deque

input = sys.stdin.readline

N = int(input())
cards = deque([i for i in range(1, N + 1)])

for i in range(N - 1):
    cards.popleft()
    cards.append(cards.popleft())

print(cards.popleft())
