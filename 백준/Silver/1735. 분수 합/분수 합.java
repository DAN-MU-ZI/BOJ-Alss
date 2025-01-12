import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		InputHandler inputHandler = new InputHandler();
		OutputHandler outputHandler = new OutputHandler();

		Solution solution = new Solution();

		int[] input = new int[4];
		input[0] = inputHandler.readInt();
		input[1] = inputHandler.readInt();
		input[2] = inputHandler.readInt();
		input[3] = inputHandler.readInt();

		String result = solution.solve(input);
		outputHandler.write(String.valueOf(result));

		outputHandler.flush();
		outputHandler.close();
		inputHandler.close();
	}

	private static class InputHandler {
		private final BufferedReader br;
		private StringTokenizer st;

		public InputHandler() {
			this.br = new BufferedReader(new InputStreamReader(System.in));
		}

		public int readInt() throws IOException {
			if (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
			}
			return Integer.parseInt(st.nextToken());
		}

		public void close() throws IOException {
			br.close();
		}
	}

	private static class OutputHandler {
		private final BufferedWriter bw;
		private final StringBuilder sb;

		public OutputHandler() {
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			sb = new StringBuilder();
		}

		public void write(String result) {
			sb.append(result).append("\n");
		}

		public void flush() throws IOException {
			bw.write(sb.toString());
		}

		public void close() throws IOException {
			bw.flush();
			bw.close();
		}
	}

	private static class Solution {
		private static int gcd(int a, int b) {
			int tmp;
			while (true) {
				tmp = a % b;
				if (tmp == 0) break;
				a = b;
				b = tmp;
			}
			return b;
		}

		public Fraction mul(Fraction f, int mul) {
			return new Fraction(f.numerator * mul, f.denominator * mul);
		}

		public Fraction sum(Fraction f1, Fraction f2) {
			return new Fraction(f1.numerator + f2.numerator, f1.denominator);
		}

		public Fraction simplify(Fraction f) {
			int div = gcd(f.numerator, f.denominator);
			return new Fraction(f.numerator/div, f.denominator/div);
		}

		public String solve(int[] input) {
			Fraction f1 = new Fraction(input[0], input[1]);
			Fraction f2 = new Fraction(input[2], input[3]);

			int div = gcd(f1.denominator, f2.denominator);
			int f1Mul = f2.denominator / div;
			int f2Mul = f1.denominator / div;
			f1 = mul(f1, f1Mul);
			f2 = mul(f2, f2Mul);

			Fraction f = sum(f1, f2);
			f = simplify(f);


			return String.format("%d %d\n", f.numerator, f.denominator);
		}

		static class Fraction {
			int numerator;
			int denominator;

			public Fraction (int numerator, int denominator) {
				this.numerator = numerator;
				this.denominator = denominator;
			}
		}
	}
}