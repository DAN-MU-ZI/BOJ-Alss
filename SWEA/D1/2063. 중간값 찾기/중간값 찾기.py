N = int(input())
arr = list(map(int, input().split()))
arr.sort()
print(arr[N//2])