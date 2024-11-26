import sys

input = sys.stdin.readline

arr = []
while True:
    line = input().strip()
    if line == "-":
        break

    tmp = [0] * 26
    for c in line:
        tmp[ord(c) - ord("A")] += 1
    arr.append(tmp)

puzzles = []
while True:
    line = list(input().strip())
    if line[0] == "#":
        break
    puzzles.append(line)

for puzzle in puzzles:
    counts = [0] * 26
    visited = [False] * 26
    alpha = [0] * 26
    for c in puzzle:
        alpha[ord(c) - ord("A")] += 1

    for i in range(9):
        cur = ord(puzzle[i]) - ord("A")
        if visited[cur]:
            continue
        visited[cur] = True

        count = 0
        for word in arr:
            flag = True
            for j in range(26):
                if word[j] > alpha[j]:
                    flag = False
                    break
            if flag and word[cur] > 0:
                count += 1

        counts[cur] = count

    _min = len(arr)
    _max = 0

    for i in range(26):
        if alpha[i]:
            _min = min(_min, counts[i])
            _max = max(_max, counts[i])

    min_chars = ""
    max_chars = ""
    for i in range(26):
        if not alpha[i]:
            continue
        if counts[i] == _min:
            min_chars += chr(i + 65)
        if counts[i] == _max:
            max_chars += chr(i + 65)

    print(min_chars, _min, max_chars, _max)
