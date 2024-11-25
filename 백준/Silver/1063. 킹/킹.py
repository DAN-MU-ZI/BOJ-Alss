import sys

input = sys.stdin.readline

col_to_index = {"A": 1, "B": 2, "C": 3, "D": 4, "E": 5, "F": 6, "G": 7, "H": 8}
index_to_col = {v: k for k, v in col_to_index.items()}

K, S, N = input().split()
Kc, Kr = list(K)
Kc = col_to_index[Kc]
Kr = int(Kr)
Sc, Sr = list(S)
Sc = col_to_index[Sc]
Sr = int(Sr)
N = int(N)
move = [input().strip() for _ in range(N)]

directions = {
    "R": (0, 1),
    "L": (0, -1),
    "B": (-1, 0),
    "T": (1, 0),
    "RT": (1, 1),
    "LT": (1, -1),
    "RB": (-1, 1),
    "LB": (-1, -1),
}


def is_out_of_bound(r, c):
    return 1 > r or r > 8 or 1 > c or c > 8


while move:
    m = move.pop(0)
    dr, dc = directions[m]

    Knr, Knc = Kr + dr, Kc + dc
    if Knr == Sr and Knc == Sc:
        Snr, Snc = Sr + dr, Sc + dc

        if is_out_of_bound(Snr, Snc):
            continue

        Kr, Kc = Knr, Knc
        Sr, Sc = Snr, Snc

    elif is_out_of_bound(Knr, Knc):
        continue

    Kr, Kc = Knr, Knc


print(index_to_col[Kc] + str(Kr))
print(index_to_col[Sc] + str(Sr))
