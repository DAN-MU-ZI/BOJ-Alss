import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static String[] people;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        people = new String[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            people[i] = st.nextToken();
        }
        Arrays.sort(people);

        Map<String, List<String>> parents = new HashMap<>();
        Map<String, List<String>> childs = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();
        Map<String, TreeSet<String>> answer = new HashMap<>();

        for (String person : people) {
            parents.put(person, new ArrayList<>());
            childs.put(person, new ArrayList<>());
            indegree.put(person, 0);
            answer.put(person, new TreeSet<>());
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] tokens = br.readLine().split(" ");
            String child = tokens[0];
            String parent = tokens[1];

            parents.get(child).add(parent);
            childs.get(parent).add(child);
            indegree.put(child, indegree.get(child) + 1);
        }

        TreeSet<String> roots = new TreeSet<>();
        for (String person : people) {
            if (parents.get(person).isEmpty()) {
                roots.add(person);
            }
        }

        sb.append(roots.size() + "\n");
        for (String root : roots) {
            sb.append(root + " ");
        }
        sb.append("\n");

        for (String root : roots) {
            topologicalSort(root, childs, indegree, answer);
        }

        for (String person : people) {
            sb.append(person + " " + answer.get(person).size());
            for (String child : answer.get(person)) {
                sb.append(" " + child);
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    static void topologicalSort(String root, Map<String, List<String>> childs,
            Map<String, Integer> indegree, Map<String, TreeSet<String>> answer) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            String cur = queue.poll();
            for (String child : childs.get(cur)) {
                // 자식의 진입차수를 감소시키고
                indegree.put(child, indegree.get(child) - 1);
                // 진입차수가 0이 되면, cur가 해당 자식의 직접 부모로 판단
                if (indegree.get(child) == 0) {
                    answer.get(cur).add(child);
                    queue.offer(child);
                }
            }
        }
    }
}