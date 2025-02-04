import java.io.*;
import java.util.*;

class Solution
{
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = 10;
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] bundle = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                bundle[i] = Integer.parseInt(st.nextToken());
            }
            
            int M = Integer.parseInt(br.readLine());
            String commands = br.readLine();            

            String answer = solve(N, bundle, M, commands);
            sb.append(String.format("#%d %s\n", t, answer));
        }

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

    private static String solve(int N, int[] bundle, int M, String commands) {
        // init
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            list.add(bundle[i]);
        }

        // processing commands
        StringTokenizer st = new StringTokenizer(commands);
        int x, y;
        List<Integer> tmp;
        for (int i = 0; i < M; i++) {
            // parse command
            char command = st.nextToken().charAt(0);
            switch (command) {
                case 'I':
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    tmp = new LinkedList<>();
                    for (int j = 0; j < y; j++) {
                        int s = Integer.parseInt(st.nextToken());
                        tmp.add(s);
                    }
                    list.addAll(x, tmp);
                    break;
                case 'D':
                    x = Integer.parseInt(st.nextToken());
                    y = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < y; j++) {
                        list.remove(x);
                    }
                    break;
                case 'A':
                    y = Integer.parseInt(st.nextToken());
                    tmp = new LinkedList<>();
                    for (int j = 0; j < y; j++) {
                        int s = Integer.parseInt(st.nextToken());
                        tmp.add(s);
                    }
                    list.addAll(tmp);
                    break;
            }
        }

        return String.join(
            " ", list.stream()
            		.limit(10)
                    .map(String::valueOf)
                    .toArray(String[]::new)
            );
    }
}