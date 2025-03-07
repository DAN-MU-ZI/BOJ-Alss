import java.io.*;
import java.util.*;

public class Main {
    static class Problem implements Comparable<Problem> {
        int id, level, category;

        public Problem(int _id, int _level, int _category) {
            id = _id;
            level = _level;
            category = _category;
        }

        public int compareTo(Problem p) {
            if (level == p.level)
                return id - p.id;
            return level - p.level;
        }
    }

    static TreeSet<Problem> set;
    static HashMap<Integer, Problem> map;
    static TreeSet<Problem>[] group;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        set = new TreeSet<>();
        map = new HashMap<>();
        group = new TreeSet[101];
        for (int i = 0; i < 101; i++) {
            group[i] = new TreeSet<>();
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            int category = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(id, level, category);
            set.add(problem);
            map.put(id, problem);
            group[category].add(problem);
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken().trim();

            if (command.equals("recommend")) {
                int G = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    sb.append(group[G].last().id + "\n");
                } else {
                    sb.append(group[G].first().id + "\n");
                }
            } else if (command.equals("recommend2")) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    sb.append(set.last().id + "\n");
                } else {
                    sb.append(set.first().id + "\n");
                }
            } else if (command.equals("recommend3")) {
                int x = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());

                Problem filter = new Problem(-1, L, -1);
                Problem query = null;
                if (x == 1) {
                    query = set.higher(filter);
                } else {
                    query = set.floor(filter);
                }
                sb.append((query == null ? -1 : query.id) + "\n");
            } else if (command.equals("add")) {
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                int G = Integer.parseInt(st.nextToken());
                Problem problem = new Problem(P, L, G);
                set.add(problem);
                map.put(P, problem);
                group[G].add(problem);
            } else if (command.equals("solved")) {
                int P = Integer.parseInt(st.nextToken());
                Problem problem = map.get(P);
                set.remove(problem);
                group[problem.category].remove(problem);
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
