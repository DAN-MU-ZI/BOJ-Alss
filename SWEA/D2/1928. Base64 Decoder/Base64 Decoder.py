char_dict = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/'
char_dict = {k:i for i, k in enumerate(char_dict)}

T = int(input())
for test_case in range(1, T + 1):
    line = input()
    result = ''
    for i in range(0, len(line), 4):
        buffer = 0
        for c in line[i:i + 4]:
            buffer = buffer << 6 | char_dict[c]
        tmp = ''
        for j in range(3):
            char = buffer & 0xFF
            tmp = chr(char) + tmp
            buffer = buffer >> 8
        result+=tmp    
    print(f'#{test_case} {result}')
