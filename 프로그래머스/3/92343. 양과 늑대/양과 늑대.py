from collections import defaultdict



def solution(info, edges):
    global answer
    answer = 0
    
    SHEEP = 0
    WOLF = 1
    
    # graph 생성
    graph = defaultdict(list)
    for a, b in edges:
        graph[a].append(b)
    
    print(graph)
    
    # visited 생성
    visited = [False for _ in range(len(info))]
    
    def dfs(node, sheep_count, wolf_count, visitable):
        global answer
        
        if info[node]==SHEEP:
            sheep_count+=1
        else:
            wolf_count+=1
        
        if sheep_count > wolf_count:
            answer = max(answer, sheep_count)
        else:
            return
        
        for next_node in graph[node]:
            visitable.append(next_node)
        
        for next_node in visitable:
            next_visitable = visitable.copy()
            next_visitable.remove(next_node)
            dfs(next_node, sheep_count, wolf_count, next_visitable)
        
        
    dfs(0,0,0, [])

    return answer
    
    


        