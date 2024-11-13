T = int(input())
for test_case in range(1, T + 1):
    N, K = map(int, input().split())
    scores = [list(map(int, input().split())) for _ in range(N)]
    
    total = []
    for i, score in enumerate(scores):
        total_score = score[0] * 0.35 + score[1] * 0.45 + score[2] * 0.2
        total.append((total_score, i + 1)) 
        
    total.sort(reverse=True, key=lambda x: x[0])
    
    grade_dict = ['A+', 'A0', 'A-', 'B+', 'B0', 'B-', 'C+', 'C0', 'C-', 'D0']
    students_per_grade = N // 10  # 각 학점에 들어갈 학생 수
    grades = {}
    
    for i in range(N):
        grade = grade_dict[i//students_per_grade]
        grades[total[i][1]] = grade
    
    answer = grades[K]
    print(f'#{test_case} {answer}')