import sys
sys.setrecursionlimit(1000000)

n,m,v = map(int, sys.stdin.readline().split())

net_di = {k+1:set() for k in range(n)}
for i in range(m):
    fr, to = map(int, sys.stdin.readline().split())
    net_di[fr].add(to)
    net_di[to].add(fr)

for k,value in net_di.items():
    net_di[k] = sorted(list(value))

prev = []
stk = []
def dfs(k):
    if k not in prev:
        prev.append(k)
        stk.append(k)
        
        if net_di[k]:
            for i in net_di[k]:
                if i not in prev and net_di[i]:
                    dfs(i)
dfs(v)
print(" ".join(map(str,stk)))

stk = []
def bfs(k):
    visited = [k]
    queue = [k]
    while queue:
        tmp = queue.pop(0)
        stk.append(tmp)
        for v in net_di[tmp]:
            if v not in visited and net_di[v]:
                queue.append(v)
                visited.append(v)
bfs(v)
print(" ".join(map(str,stk)))