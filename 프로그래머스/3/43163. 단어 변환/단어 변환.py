def solution(begin, target, words):
    # 문제 정의
    # 한번에 한 개의 알파벳만 바꾸는데, 그 단어는 words 에 있는 목록이어야만 한다
    # 변환 가능한 단어는 알파벳 불일치 개수가 1개일때만이다.
    # -> 현 위치에서 이동가능한 words 목록화 하기
    # 처음부터 목표 단어로 도달할 때까지 반복한다.
    # 만일 모두 방문해도 목표 단어에 도달하지 못한다면 0을 반환한다.
    # 목표단어가 words 안에 없다면 오류이므로 0을 반환한다.
    # -> 어떻게 순회하지?
    
    # 필요한 정보 및 동작
    # visited 정보 -> 앞에서 받은 정보와 다음으로 넘어갈때 넘겨줄 정보 포함
    # 다음으로 이동가능한 words 정보
    # 순회 방식 -> 다음으로 이동가능한 words 인덱스를 받고 하나씩 넘겨주기
    # 남은 words
    answer = 0
    def accessible(word1, word2):
        neq_cnt = 0
        for i in range(len(word1)):
            if word1[i]!=word2[i]:
                neq_cnt += 1
        if neq_cnt==1:
            return True
        else:
            return False

    def dfs(begin, words, visited, stk):
        nonlocal answer, target
        accessible_words = [w for w in words if accessible(begin, w)]
        # print(begin, accessible_words, visited)
        if begin==target:
            if answer==0: # 처음이 아니라면
                answer=stk
            elif answer > stk:
                answer=stk
        if len(accessible_words)==0: return
        
        for word in accessible_words:
            remain_words = [w for w in words if w != word]
            new_visit = visited.copy()
            new_visit[word]=True
            dfs(word, remain_words, new_visit, stk+1)
            
    visited = {k:False for k in words}
    dfs(begin, words, visited, 0)
    
    return answer