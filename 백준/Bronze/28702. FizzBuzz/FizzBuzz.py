import sys

input = sys.stdin.readline

arr = [input().strip() for _ in range(3)]


def get_number(arr):
    for i in range(3):
        if str.isdigit(arr[i]):
            return int(arr[i]) + (3 - i)


num = get_number(arr)


def calc_result(num):
    con_3 = num % 3 == 0
    con_5 = num % 5 == 0

    if con_3 and con_5:
        return "FizzBuzz"
    if con_3 and not con_5:
        return "Fizz"
    if not con_3 and con_5:
        return "Buzz"
    if not con_3 and not con_5:
        return num

    return num


answer = calc_result(num)
print(answer)
