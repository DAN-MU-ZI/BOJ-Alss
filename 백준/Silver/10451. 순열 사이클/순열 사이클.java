import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 처리하기 위한 BufferedReader, 출력용 BufferedWriter 준비
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 테스트 케이스 개수 입력
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            // 순열 크기 N
            int N = Integer.parseInt(br.readLine());
            
            // 순열 입력
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N + 1]; // 1-based indexing
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // 유니온 파인드 클래스 생성
            UnionFind uf = new UnionFind(N);

            // Union 연산 수행 (i와 arr[i] 연결)
            for (int i = 1; i <= N; i++) {
                uf.union(i, arr[i]);
            }

            // 사이클 개수 계산: 유일한 루트 노드의 개수
            sb.append(uf.getUniqueRootCount()).append("\n");
        }

        // 결과 출력
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 유니온 파인드 클래스 정의
    static class UnionFind {
        private int[] parent;

        // 초기화: 각 노드는 자기 자신을 부모로 가짐
        public UnionFind(int n) {
            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }

        // Find 연산: 경로 압축 적용
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // 경로 압축
            }
            return parent[x];
        }

        // Union 연산: 두 노드의 루트를 병합
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootY] = rootX; // y의 루트를 x의 루트로 변경
            }
        }

        // 유일한 루트 노드의 개수를 반환
        public int getUniqueRootCount() {
            Set<Integer> uniqueRoots = new HashSet<>();
            for (int i = 1; i < parent.length; i++) {
                uniqueRoots.add(find(i)); // 각 노드의 최종 루트를 찾음
            }
            return uniqueRoots.size();
        }
    }
}
