import sys
sys.setrecursionlimit(1000000)

computers = int(sys.stdin.readline())
relations = int(sys.stdin.readline())
net_di = {k+1:set() for k in range(computers)}
net_set = set([x+1 for x in range(computers)])
for i in range(relations):
    fr, to = map(int, sys.stdin.readline().split())
    net_di[fr].add(to)
    net_di[to].add(fr)

viruses = set([1])
def func(start, prev):
    if len(net_di[start]):
        for x in net_di[start]:
            if x not in prev:
                tmp = prev
                tmp.append(x)
                viruses.add(x)
                func(x,tmp)

func(1,[1])
if 1 in viruses:
    viruses.remove(1)
print(len(viruses))