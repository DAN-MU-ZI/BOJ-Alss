import java.io.*;
import java.util.*;

public class Main {

	static class TrieNode {
		TrieNode[] child = new TrieNode[2];
		int count = 0;
	}

	static class Trie {
		private final TrieNode root = new TrieNode();

		public void insert(int x) {
			TrieNode node = root;
			for (int i = 31; i >= 0; i--) {
				int b = (x >> i) & 1;
				if (node.child[b] == null) {
					node.child[b] = new TrieNode();
				}
				node = node.child[b];
				node.count++;
			}
		}

		public void erase(int x) {
			TrieNode node = root;
			for (int i = 31; i >= 0; i--) {
				int b = (x >> i) & 1;
				node = node.child[b];
				node.count--;
			}
		}

		public int maxXor(int k) {
			TrieNode node = root;
			int ans = 0;
			for (int i = 31; i >= 0; i--) {
				int b = (k >> i) & 1;
				int opp = 1 - b;

				if (node==null) {
					break;
				}

				if (node.child[opp] != null && node.child[opp].count > 0) {
					ans |= (1 << i);
					node = node.child[opp];
				} else {
					node = node.child[b];
				}
			}

			return ans;
		}
	}

	private static final int INSERT = 1;
	private static final int ERASE = 2;
	private static final int MAXXOR = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		Trie trie = new Trie();
		trie.insert(0);

		int M = Integer.parseInt(br.readLine());
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			if (command == INSERT) {
				trie.insert(x);
			} else if (command == ERASE) {
				trie.erase(x);
			} else if (command == MAXXOR) {
				sb.append(trie.maxXor(x)).append('\n');
			}
		}
		System.out.println(sb);
	}

}
