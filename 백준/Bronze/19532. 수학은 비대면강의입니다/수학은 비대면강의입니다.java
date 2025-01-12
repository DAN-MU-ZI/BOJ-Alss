import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int f = Integer.parseInt(st.nextToken());

		Formula f1 = new Formula(a, b, c);
		Formula f2 = new Formula(d, e, f);

		Formula formula, f3, f4;
		f3 = mul(f1, f2.y);
		f4 = mul(f2, f1.y);
		formula = sub(f3, f4);
		int x = formula.a / formula.x;

		f3 = mul(f1, f2.x);
		f4 = mul(f2, f1.x);
		formula = sub(f3, f4);
		int y = formula.a / formula.y;

		bw.write(String.format("%d %d\n", x, y));
		bw.flush();
		bw.close();
		br.close();
	}

	public static Formula mul(Formula f, int mul) {
		return new Formula(f.x * mul, f.y * mul, f.a * mul);
	}

	public static Formula sub(Formula f1, Formula f2) {
		return new Formula(f1.x - f2.x, f1.y - f2.y, f1.a - f2.a);
	}
}

class Formula {
	int x;
	int y;
	int a;

	public Formula(int x, int y, int a) {
		this.x = x;
		this.y = y;
		this.a = a;
	}
}