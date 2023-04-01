n = int(input())

a, b, c = map(int, input().split())

result = sum(a * i + b * j + c * k <= n - 1 for i in range(n) for j in range(n - a * i) for k in range(n - a * i - b * j))

print(result)