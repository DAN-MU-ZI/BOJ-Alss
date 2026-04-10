import sys
from heapq import heappush, heappop

def solution():
    input = sys.stdin.readline
    
    # 1. 입력 받기
    nv, ne = map(int, input().split())
    k = int(input())
    
    # 2. 그래프 구조 최적화: 딕셔너리 대신 인접 리스트(List of Lists) 사용이 훨씬 빠름
    graph = [[] for _ in range(nv + 1)]
    for _ in range(ne):
        u, v, w = map(int, input().split())
        graph[u].append((v, w))
        
    # 3. 안전한 INF 값 설정
    INF = int(1e9) 
    distance = [INF] * (nv + 1)
    distance[k] = 0
    
    q = []
    heappush(q, (0, k))
    
    # 4. 표준 다익스트라 실행
    while q:
        dist, now = heappop(q)
        
        # 이미 처리된 (더 짧은 경로가 있는) 노드는 무시
        if distance[now] < dist:
            continue
            
        for nxt_node, weight in graph[now]:
            cost = dist + weight
            
            # 더 짧은 경로를 발견한 경우에만 큐에 삽입
            if cost < distance[nxt_node]:
                distance[nxt_node] = cost
                heappush(q, (cost, nxt_node))
                
    # 5. 결과 출력
    for i in range(1, nv + 1):
        if distance[i] == INF:
            print("INF")
        else:
            print(distance[i])

if __name__ == '__main__':
    solution()