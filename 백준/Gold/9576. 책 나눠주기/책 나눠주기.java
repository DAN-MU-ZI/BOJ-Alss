import java.io.*;
import java.util.*;

public class Main {
    static class BookRequest implements Comparable<BookRequest> {
        int start;
        int end;

        public BookRequest(int a, int b) {
            this.start = a;
            this.end = b;
        }

        public int compareTo(BookRequest o) {
            if (this.end == o.end) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            List<BookRequest> requests = new ArrayList<>();
            boolean[] isBookTaken = new boolean[N + 1];

            while (M --> 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                requests.add(new BookRequest(a, b));
            }

            Collections.sort(requests);

            int count = 0;
            for(BookRequest req: requests) {
                for (int i = req.start; i <= req.end; i++) {
                    if (!isBookTaken[i]) {
                        isBookTaken[i] = true;
                        count++;
                        break;
                    }
                }
            }
            result.append(count).append("\n");
        }
        System.out.println(result);
    }
}