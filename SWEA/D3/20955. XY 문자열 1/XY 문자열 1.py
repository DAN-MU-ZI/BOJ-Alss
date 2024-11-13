def dfs(s, e):
    global answer

    if not e:
        return
    if answer == "Yes":
        return

    if s == e:
        answer = "Yes"

    if e[-1] == "X":
        dfs(s, e[:-1])

    if e[-1] == "Y":
        dfs(s, e[:-1][::-1])


T = int(input())
for test_case in range(1, T + 1):
    global answer
    answer = "No"
    S = input().strip()
    E = input().strip()
    dfs(S, E)
    print(f"#{test_case} {answer}")
