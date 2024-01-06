import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			System.out.println(func(br));
		}
	}

	private static ArrayList<Integer> convert(int n, int b) {
		ArrayList<Integer> list = new ArrayList<>();
		while (n > 0) {
			list.add(n % b);
			n = n / b;
		}
		return list;
	}

	private static boolean checkPalindrome(ArrayList<Integer> n) {
		for (int i = 0; i < n.size() / 2; i++) {
			if (!Objects.equals(n.get(i), n.get(n.size() - i - 1))) {
				return false;
			}
		}
		return true;
	}

	private static int func(BufferedReader br) throws IOException {
		int n = Integer.parseInt(br.readLine());
		for (int i = 2; i <= 64; i++) {
			ArrayList<Integer> list = convert(n, i);
			if (checkPalindrome(list)) {
				return 1;
			}
		}
		return 0;
	}
}
