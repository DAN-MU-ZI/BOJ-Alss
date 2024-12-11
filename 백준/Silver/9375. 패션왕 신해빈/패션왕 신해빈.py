import sys

input = sys.stdin.readline

T = int(input())
for _ in range(T):
    global answer
    answer = 0
    n = int(input())
    fashion_dict = dict()
    for _ in range(n):
        name, kind = input().strip().split()
        if not fashion_dict.get(kind):
            # fashion_dict[kind] = [name]
            fashion_dict[kind] = 1
        else:
            # fashion_dict[kind].append(name)
            fashion_dict[kind] += 1

    tmp = 1
    for k, v in fashion_dict.items():
        tmp *= v + 1

    print(tmp - 1)
