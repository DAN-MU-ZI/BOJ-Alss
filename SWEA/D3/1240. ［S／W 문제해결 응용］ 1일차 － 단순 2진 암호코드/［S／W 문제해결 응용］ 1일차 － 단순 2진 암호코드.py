def is_valid(num):
    even = 0
    odd = 0

    for i in range(8):
        if i % 2:
            odd += num % 10
        else:
            even += num % 10
        num = num // 10

    if (odd * 3 + even) % 10:
        return False

    return True


decode_book = {
    "0001101": 0,
    "0011001": 1,
    "0010011": 2,
    "0111101": 3,
    "0100011": 4,
    "0110001": 5,
    "0101111": 6,
    "0111011": 7,
    "0110111": 8,
    "0001011": 9,
}


def decode(buffer):
    return decode_book[buffer]


def solution(arr):
    acc = 0
    endpoint = -1

    i = 0
    while i < len(arr):
        line = arr[i]
        if "1" not in line:
            i += 1
            continue

        for i in range(len(line) - 1, -1, -1):
            if line[i] == "1":
                endpoint = i + 1
                break

        if endpoint != -1:
            break

    line = line[endpoint - 56 : endpoint]
    num = 0
    acc = 0
    for j in range(8):
        buffer = line[j * 7 : j * 7 + 7]

        dec = decode(buffer)
        num *= 10
        num += dec
        acc += dec

    if is_valid(num):
        return acc

    return 0


T = int(input())
for test_case in range(1, T + 1):
    answer = 0

    N, M = map(int, input().split())
    arr = [input() for _ in range(N)]

    answer = solution(arr)

    print(f"#{test_case} {answer}")
