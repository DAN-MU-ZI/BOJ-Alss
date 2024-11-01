def swap(a, b):
    return b, a


def arr_to_int(arr):
    return int("".join(arr))


def dfs(arr, remain):
    global answer
    if remain == 0:
        num = arr_to_int(arr)
        answer = max(answer, num)
        return

    for i in range(len(arr) - 1):
        for j in range(i + 1, len(arr)):
            arr[i], arr[j] = swap(arr[i], arr[j])

            num = arr_to_int(arr)
            if num not in save[remain]:
                dfs(arr, remain - 1)
                save[remain].add(num)

            arr[i], arr[j] = swap(arr[i], arr[j])


T = int(input())
for test_case in range(1, T + 1):
    global answer
    answer = 0
    arr, count = input().split()
    arr = list(arr)
    save = {k: set() for k in range(11)}

    count = int(count)

    dfs(arr, count)
    print(f"#{test_case} {answer}")
