import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] spells = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			spells[i][0] = Integer.parseInt(st.nextToken()) - 1;
			spells[i][1] = Integer.parseInt(st.nextToken());
		}

		Solution solution = new Solution(N, M, map, spells);
		int answer = solution.solve();
		sb.append(answer);

		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static class Solution {
		class Ball {
			int num;

			public Ball(int num) {
				this.num = num;
			}
		}

		int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

		final int r, c;
		int N, M;
		int[][] spells;
		Queue<Ball> dq;
		List<int[]> positions = new ArrayList<>();

		public Solution(int N, int M, int[][] map, int[][] spells) {
			r = N / 2;
			c = N / 2;
			this.N = N;
			this.M = M;
			this.spells = spells;

			init(map);
		}

		private void init(int[][] map) {
			dq = new ArrayDeque<>();
			int cnt = 0;
			int step = 0;
			int d = 0;
			int distance = 1;
			int nr = r;
			int nc = c;
			int[][] deltas = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
			while (positions.size() < N * N - 1) {
				nr += deltas[d][0];
				nc += deltas[d][1];
				positions.add(new int[] {nr, nc});
				if (map[nr][nc] != 0) {
					Ball ball = new Ball(map[nr][nc]);
					dq.add(ball);
				}

				step++;
				if (step == distance) {
					step = 0;
					cnt++;
					d = (d + 1) % 4;
				}
				if (cnt == 2) {
					distance++;
					cnt = 0;
				}
			}
		}

		public int solve() {
			int answer = 0;
			for (int[] spell : spells) {
				// System.out.println(dq.size());
				int d = spell[0];
				int s = spell[1];

				cast(d, s);
				// System.out.println(dq.size());
				int explodeCnt = -1;
				while (explodeCnt != 0) {
					explodeCnt = explodeBallGroup();
					answer += explodeCnt;
				}
				// System.out.println("answer = " + answer);
				// System.out.println(dq.size());
				grouping();
				// System.out.println(dq.size());
			}
			// System.out.println(dq.size());
			return answer;
		}

		private int explodeBallGroup() {
			Deque<Ball> nextDq = new ArrayDeque<>();
			int seqCnt = 0;

			int explodeCnt = 0;
			Deque<Ball> target = new ArrayDeque<>();
			while (!dq.isEmpty()) {
				Ball ball = dq.poll();
				if (target.isEmpty()) {
					seqCnt = 0;
				} else {
					if (target.peek().num != ball.num) {
						if (seqCnt >= 4) {
							explodeCnt += target.size() * target.peek().num;
							while (!target.isEmpty()) {
								target.poll();
							}
							// System.out.println("explodeCnt = " + explodeCnt);
						} else {
							while (!target.isEmpty()) {
								nextDq.add(target.poll());
							}
						}
						seqCnt = 0;
					}
				}
				target.add(ball);
				seqCnt++;
				// System.out.printf("dq: %d, next: %d, ball: %d, seq: %d\n", dq.size(), nextDq.size(), ball.num, seqCnt);
			}

			if (seqCnt >= 4 && !target.isEmpty()) {
				explodeCnt += target.size() * target.peek().num;
				while (!target.isEmpty()) {
					target.poll();
				}
			} else {
				while (!target.isEmpty()) {
					nextDq.add(target.poll());
				}
			}
			dq = nextDq;
			return explodeCnt;
		}

		private void grouping() {
			Deque<Ball> nextDq = new ArrayDeque<>();

			Deque<Ball> target = new ArrayDeque<>();
			while (!dq.isEmpty()) {
				Ball ball = dq.poll();
				if (!target.isEmpty() && target.peek().num != ball.num) {
					group(nextDq, target);
				}
				target.add(ball);
				// System.out.printf("dq: %d, next: %d, ball: %d\n", dq.size(), nextDq.size(), ball.num);
			}

			group(nextDq, target);
			dq = nextDq;
		}

		private void group(Deque<Ball> nextDq, Deque<Ball> target) {
			if (isFull(nextDq) || target.isEmpty())
				return;

			Ball head = target.getFirst();
			Ball A = new Ball(target.size());
			nextDq.add(A);

			if (!isFull(nextDq)) {
				Ball B = new Ball(head.num);
				nextDq.add(B);
			}
			target.clear();

		}

		private boolean isFull(Deque<Ball> dq) {
			return dq.size() == N * N - 1;
		}

		private void cast(int d, int s) {
			int[] delta = deltas[d];
			int dr = delta[0];
			int dc = delta[1];

			int nr = r;
			int nc = c;

			Deque<Ball> nextDq = new ArrayDeque<>();
			Iterator<Ball> dqIt = this.dq.iterator();
			Iterator<int[]> posIt = positions.iterator();
			for (int i = 0; i < s; i++) {
				nr += dr;
				nc += dc;
				if (isValid(nr, nc)) {
					while (posIt.hasNext() && dqIt.hasNext()) {
						int[] pos = posIt.next();
						if (nr == pos[0] && nc == pos[1]) {
							dqIt.next();
							break;
						} else {
							nextDq.add(dqIt.next());
						}
					}
				}
			}

			while (dqIt.hasNext()) {
				nextDq.add(dqIt.next());
			}

			dq = nextDq;
		}

		boolean isValid(int r, int c) {
			return 0 <= r && r < N && 0 <= c && c < N;
		}
	}
}
