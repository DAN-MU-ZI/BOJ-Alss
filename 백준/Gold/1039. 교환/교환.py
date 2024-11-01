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
            if num not in save[remain] and int(arr[0]) != 0:
                dfs(arr, remain - 1)
                save[remain].add(num)

            arr[i], arr[j] = swap(arr[i], arr[j])


global answer
answer = -1
arr, count = input().split()
arr = list(arr)
count = int(count)
save = {k: set() for k in range(count + 1)}


dfs(arr, count)
print(answer)
