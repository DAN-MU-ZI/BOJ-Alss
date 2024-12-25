def solution(n, computers):
    answer = 0
    parent = [i for i in range(n)]
    rank = [0] * n
    
    def find(x):
        if parent[x]!=x:
            parent[x] = find(parent[x])
        return parent[x]
    
    def union(x, y):
        rootX = find(x)
        rootY = find(y)
        
        if rootX == rootY:
            return
        
        if rank[rootX] < rank[rootY]:
            parent[rootX] = rootY
        elif rank[rootY] > rank[rootX]:
            parent[rootY] = rootX
        else:
            parent[rootY] = rootX
            rank[rootX] += 1
    
    for computer in computers:
        connect_list = []
        for j in range(n):
            if computer[j]:
                connect_list.append(j)
        if len(connect_list) > 1:
            for j in range(1, len(connect_list)):
                union(connect_list[0], connect_list[j])
    
    return len(set(parent))