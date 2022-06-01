
print("=" * 50)
print("Kruskal's Algorithm")
print("20194146 이승진")
print("=" * 50)

node = int(input("node의 개수 >> "))
group_list = [i for i in range(node)]
graph = [[-1] * node for _ in range(node)]
edge_list = []

# 간선 및 가중치 입력
print("=" * 50)
print("출발 노드, 도착 노드, 가중치를 순서대로 입력하시오(구분은 공백 1칸)")
print("입력을 종료하시려면 입력 없이 [Enter]키를 누르시오.")

while True:
    inputed_edge = list(map(int, input(" >> ").split()))
    if inputed_edge == []:
        break
    edge_list.append(inputed_edge)

print("=" * 50)

# 간선을 가중치의 오름차순으로 정렬
edge_list = sorted(edge_list, key=lambda x: x[2])

# kruskal 최소 신장 트리 알고리즘
for e in edge_list:

    # 같은 집합에 속할 경우 무시
    if group_list[e[0]] == group_list[e[1]]:
        continue

    # 합집합 연산
    temp_before = group_list[e[0]]
    temp_after = group_list[e[1]]
    for i in range(node):
        if group_list[i] == temp_before:
            group_list[i] = temp_after

    # 그래프에 간선 추가
    graph[e[0]][e[1]] = e[2]
    graph[e[1]][e[0]] = e[2]    # 이 줄을 제거하면 방향 그래프에 대한 알고리즘 구현 가능

# 그래프 출력
for i in range(node):
    print(i, ":", end=" ")
    for j in range(node):
        if graph[i][j] != -1:
            print(f"{j}[{graph[i][j]}]", end="\t")
    print()
 
print("=" * 50)
