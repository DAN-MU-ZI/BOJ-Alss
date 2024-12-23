from collections import deque

directions = (
    (1,0),
    (-1,0),
    (0,1),
    (0,-1)
)

def is_valid_position(r, c, n, m):
    return 0 <= r < m and 0<= c < n

def bfs(arr, visited, row, col, n, m):
    count = 0
    visited_cols = set()
    stk = deque()
    stk.append([row, col])
    while stk:
        r, c = stk.popleft()
        
        if visited[r][c]:
            continue
        
        visited[r][c] = True
        count+=1
        visited_cols.add(c)
        try:
            arr[r][c]
        except Exception as e:
            print(e, r, c)
        
        for dr, dc in directions:
            nr, nc = r+dr, c+dc
            if not is_valid_position(nr, nc, n, m):
                continue
            if arr[nr][nc] == 0:
                continue
            if visited[nr][nc]:
                continue
                
            stk.append([nr, nc])
                
    return visited_cols, count

def solution(land):
    n,m = len(land[0]), len(land)
    group = [0 for _ in range(n)]
    visited = [[False] * n for _ in range(m)]
    
    for r in range(m):
        for c in range(n):
            if visited[r][c]:
                continue
            if land[r][c] == 0:
                continue
                
            visited_cols, volume = bfs(land, visited, r, c, n, m)
            for col in visited_cols:
                group[col]+=volume
    
    return max(group)