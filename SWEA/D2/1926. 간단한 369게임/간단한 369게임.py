N = int(input())
result = ""
for i in range(1, N + 1):
    i = str(i)
    tmp = ""
    for c in i:
        if c in ["3", "6", "9"]:
            tmp += "-"
    if tmp:
        result += tmp + " "
    else:
        result += i + " "
print(result)
