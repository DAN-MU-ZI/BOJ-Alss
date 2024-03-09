import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < n; i++) {
            stk.push(br.readLine().length());
        }

        long answer = 0;
        int[] arr = new int[21];
        Deque<Integer> q = new ArrayDeque<>();
        while (!stk.empty()) {
            Integer pop = stk.pop();

            if (q.isEmpty()) {
                q.push(pop);
                arr[pop]++;
            } else {
                if (q.size() == k) {
                    q.push(pop);
                    answer += arr[pop];
                    arr[pop]++;
                    Integer i = q.pollLast();
                    arr[i]--;
                } else {
                    q.push(pop);
                    answer += arr[pop];
                    arr[pop]++;
                }
            }
        }
        System.out.println(answer);
    }
}