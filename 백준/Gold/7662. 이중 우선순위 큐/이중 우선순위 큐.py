import sys
import heapq

input = sys.stdin.readline

T = int(input())
for _ in range(T):
    k = int(input())
    min_heap = []
    max_heap = []
    count_map = {}

    for _ in range(k):
        op, num = input().split()
        num = int(num)

        if op == "I":
            heapq.heappush(min_heap, num)
            heapq.heappush(max_heap, -num)
            count_map[num] = count_map.get(num, 0) + 1
        else:
            if num == 1:
                while max_heap:
                    max_val = -heapq.heappop(max_heap)
                    if count_map.get(max_val, 0) > 0:
                        count_map[max_val] -= 1
                        break
            else:
                while min_heap:
                    min_val = heapq.heappop(min_heap)
                    if count_map.get(min_val, 0) > 0:
                        count_map[min_val] -= 1
                        break
    while min_heap and count_map.get(min_heap[0], 0) == 0:
        heapq.heappop(min_heap)
    while max_heap and count_map.get(-max_heap[0], 0) == 0:
        heapq.heappop(max_heap)

    if not min_heap or not max_heap:
        print("EMPTY")
    else:
        print(-max_heap[0], min_heap[0])
