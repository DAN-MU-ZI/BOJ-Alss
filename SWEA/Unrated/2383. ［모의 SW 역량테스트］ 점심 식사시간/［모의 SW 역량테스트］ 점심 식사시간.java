import java.io.*;
import java.util.*;

class Solution {
    static int N, M;
    static int[][] people;
    static int[] s1, s2;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());
            people = new int[10][2];

            boolean isFirst = false;
            s1 = new int[3];
            s2 = new int[3];

            M = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num == 1) {
                        people[M][0] = i;
                        people[M][1] = j;
                        M++;
                    } else if (num > 1) {
                        if (isFirst) {
                            s2[0] = i;
                            s2[1] = j;
                            s2[2] = num;
                        } else {
                            s1[0] = i;
                            s1[1] = j;
                            s1[2] = num;
                            isFirst = true;
                        }
                    }
                }
            }

            sb.append(String.format("#%d %d\n", test_case, solve()));
        }
        System.out.println(sb);
    }

    static int best;

    static int solve() {
        best = Integer.MAX_VALUE;
        for (int comb = 0; comb < (1 << M); comb++) {
            // for (int comb = 0; comb < 1; comb++) {
            PriorityQueue<int[]> sq1 = new PriorityQueue<>((a1, a2) -> {
                return getDistance(s1, a1) - getDistance(s1, a2);
            });
            PriorityQueue<int[]> sq2 = new PriorityQueue<>((a1, a2) -> {
                return getDistance(s2, a1) - getDistance(s2, a2);
            });

            for (int i = 0; i < M; i++) {
                if ((comb & (1 << i)) == 0) {
                    sq1.add(people[i]);
                } else {
                    sq2.add(people[i]);
                }
            }

            simulate(sq1, sq2);
        }
        return best;
    }

    static void simulate(PriorityQueue<int[]> sq1, PriorityQueue<int[]> sq2) {
        int time = 0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        int wait1 = 0, wait2 = 0;
        while (!sq1.isEmpty() || !q1.isEmpty() || !sq2.isEmpty() || !q2.isEmpty()) {
            time++;
            // System.out.println("time : " + time);
            // if (time >= best) {
            // return;
            // }

            while (!q1.isEmpty()) {
                if (q1.peek() <= time) {
                    q1.poll();
                    // System.out.println("pop");
                } else {
                    break;
                }
            }
            while (wait1 > 0 && q1.size() < 3) {
                q1.add(time + s1[2]);
                wait1--;
            }

            while (!q2.isEmpty()) {
                if (q2.peek() <= time) {
                    q2.poll();
                    // System.out.println("pop");
                } else {
                    break;
                }
            }
            while (wait2 > 0 && q2.size() < 3) {
                q2.add(time + s2[2]);
                wait2--;
            }

            // System.out.printf("remain : %d, queue : %d, wait : %d, peek: %d\n",
            // sq1.size(), q1.size(), wait1,
            // q1.peek());

            while (!sq1.isEmpty()) {
                int d1 = getDistance(s1, sq1.peek());

                if (time >= d1) {
                    sq1.poll();
                    if (q1.size() == 3) {
                        wait1++;
                    } else {
                        q1.add(time + s1[2]);
                    }
                } else {
                    break;
                }
            }

            while (!sq2.isEmpty()) {
                int d2 = getDistance(s2, sq2.peek());

                if (time >= d2) {
                    sq2.poll();
                    if (q2.size() == 3) {
                        wait2++;
                    } else {
                        q2.add(time + s2[2]);
                    }
                } else {
                    break;
                }
            }
        }
        // System.out.println();

        best = Math.min(best, time);
    }

    static int getDistance(int[] s, int[] a) {
        return Math.abs(s[0] - a[0]) + Math.abs(s[1] - a[1]) + 1;
    }
}
